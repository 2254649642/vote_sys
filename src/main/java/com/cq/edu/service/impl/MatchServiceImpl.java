package com.cq.edu.service.impl;

import com.cq.edu.dao.MatchMapper;
import com.cq.edu.pojo.*;
import com.cq.edu.service.EventAdminService;
import com.cq.edu.service.MatchService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private EventAdminService eventAdminService;

    @Autowired
    private  Result result;


    @Override
    public List<Match> findAllMatch() {
        return matchMapper.selectAllMatchInfo();
    }

    @Override
    public Result autoGenerateMatch(String matchNumber) {
        List<Singer> singers = getAllSingerByState("0");
        int size = singers.size();
        int matNum;
        if (size < Integer.parseInt(matchNumber) * 2) {
            matNum = size / 2;
        } else {
            matNum = Integer.parseInt(matchNumber);
        }


        Random random = new Random();
        List<Integer> visitedIndexes = new LinkedList<Integer>();
        for (int i = 0; i < matNum; i++) {
            Singer singer1 = null, singer2 = null;

            int randomIndex = random.nextInt(size);
            while (visitedIndexes.contains(randomIndex)) {
                randomIndex = random.nextInt(size);
            }

            visitedIndexes.add(randomIndex);
            singer1 = singers.get(randomIndex);
            System.out.println("Random element: " + singer1);

            while (visitedIndexes.contains(randomIndex)) {
                randomIndex = random.nextInt(size);
            }

            visitedIndexes.add(randomIndex);
            singer2 = singers.get(randomIndex);
            System.out.println("Random element: " + singer2);

            if (singer1 != null && singer2 != null) {
                Match match = new Match();
                //设置匹配场次信息
                match.setPlayer_a(singer1.getReal_name());
                match.setPlayer_b(singer2.getReal_name());
                match.setA_song(singer1.getPlayer_opus());
                match.setB_song(singer2.getPlayer_opus());
                match.setPlayer_a_id(singer1.getId());
                match.setPlayer_b_id(singer2.getId());

                //设置匹配场次状态为3  投票还没有开始
                match.setState("3");
                matchMapper.insertAutoGenerateMatch(match);

                //设置歌手为已匹配状态
                singer1.setTake_state("1");
                updateSingerState(singer1.getId(), singer1.getTake_state());
                singer2.setTake_state("1");
                updateSingerState(singer2.getId(), singer1.getTake_state());
                singer1 = singer2 = null;
            }
        }
        return result;
    }

    @Override
    public List<Singer> getAllSingerByState(String state) {
        return matchMapper.selectAllSingerByState(state);
    }

    @Override
    public int updateSingerState(String id, String take_state) {
        return matchMapper.updateSingerState(id, take_state);
    }

    @Override
    public int updateMatchPoll(String player_id, String match_id) {
        return matchMapper.updateMatchPoll(player_id, match_id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result updateMatchState(String matchId, String state) {
        Match match = getMatchStateById(matchId);
        if (match.getState().equals("2")) {
            result.setCode(300);
            return result;
        } else if (match.getState().equals("1")) {
            result.setCode(400);
            return result;
        }
        matchMapper.updateMatchState(matchId, state);
        matchMapper.insertVoteDetails(match, match.getPlayer_a_id());
        matchMapper.insertVoteDetails(match, match.getPlayer_b_id());
        result.setCode(200);
        return result;
    }

    @Override
    public Result stopMatchState(String matchId, String state) {
        Match match = getMatchStateById(matchId);
        if (match.getState().equals("2") || match.getState().equals("3")) {
            result.setCode(300);
            return result;
        }

        matchMapper.updateMatchState(matchId, state);
        result.setCode(200);
        return result;
    }

    @Override
    public Match getMatchStateById(String matchId) {
        return matchMapper.selectMatchState(matchId);
    }


    @Override
    public Result<VoteDetails> getVoteDetailsByMatchId(String matchId, String playerA_id, String playerB_id) {
        VoteDetails voteDetails = matchMapper.selectVoteDetails(matchId,playerA_id,playerB_id);
        List<Score> score = matchMapper.selectScore(matchId,playerA_id,playerB_id);

        if (score.size() == 0) {
            return Result.success(200,"查询成功",voteDetails);

        } else if (score.size() == 1) {
            voteDetails.setPlayer_a_score(score.get(0).getScore());
            return Result.success(200,"查询成功",voteDetails);

        } else {
            voteDetails.setPlayer_a_score(score.get(0).getScore());
            voteDetails.setPlayer_b_score(score.get(1).getScore());
            return Result.success(200,"查询成功",voteDetails);
        }
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Result addVoteCountRecordByTick(VoteCount voteCount) {
        VoteCount voteCountTmp = getVoteCountRecordByTick(voteCount.getMatch_id());
        Match match = getMatchStateById(voteCount.getMatch_id()); //1">正在投票中 2">投票已结束 3">投票未开始
        if (match.getState().equals("2")) {
            result.setCode(600);
            return result;
        }

        if (voteCountTmp != null) {
            result.setCode(500);
            return result;
        }
        matchMapper.insertVoteCountRecordByTick(voteCount);
        matchMapper.selectLockTable(voteCount.getPlayer_id(), voteCount.getMatch_id());
        updateMatchPoll(voteCount.getPlayer_id(), voteCount.getMatch_id());

        result.setCode(200);
        return result;
    }

    @Override
    public VoteCount getVoteCountRecordByTick(String matchId) {
        return matchMapper.selectVoteCountRecordByTick(matchId);
    }

    @Transactional
    @Override
    public Result addSumScore(String matchId, String playerA_id, String playerB_id) {
        Result<VoteDetails> voteDetailsResult = getVoteDetailsByMatchId(matchId,playerA_id,playerB_id);
        String player_name_a = voteDetailsResult.getData().getPlayer_a(); //选手名称
        String player_opu_a = voteDetailsResult.getData().getA_song(); //参赛作品
        String Player_poll_a = voteDetailsResult.getData().getPlayer_a_poll(); //选手票数
        String Player_judge_score_a = voteDetailsResult.getData().getPlayer_a_score(); //评委评分
        if (Player_judge_score_a == null) {
            Player_judge_score_a = "0";
        }

        String player_name_b = voteDetailsResult.getData().getPlayer_b(); //选手名称
        String player_opu_b = voteDetailsResult.getData().getB_song(); //参赛作品
        String Player_poll_b = voteDetailsResult.getData().getPlayer_b_poll(); //选手票数
        String Player_judge_score_b = voteDetailsResult.getData().getPlayer_b_score(); //评委评分
        if (Player_judge_score_b == null) {
            Player_judge_score_b = "0";
        }

        SumScore tmp = matchMapper.selectSumScoreExist(matchId, playerA_id);
        Match match = getMatchStateById(matchId); //1">正在投票中 2">投票已结束 3">投票未开始
        if (tmp != null) {
            result.setCode(500);
            result.setMessage("该比赛已经统计过分数请去排行榜查看");
            return result;
        }

        if (match.getState().equals("1") || match.getState().equals("3")) {
            result.setCode(500);
            result.setMessage("该比赛尚未停止投票，无法统计分数");
            return result;
        }

        SumScore sumScore = new SumScore();

        //统计A选手分数
        sumScore.setMatch_id(matchId);
        sumScore.setPlayer_id(playerA_id);
        sumScore.setPlayer_opus(player_opu_a);
        sumScore.setPlayer_name(player_name_a);


        Integer sumScoreTmp = Integer.parseInt(Player_poll_a) + Integer.parseInt(Player_judge_score_a); //计算总分
        sumScore.setSum_score(sumScoreTmp.toString());
        matchMapper.insertSumScore(sumScore);

        //统计B选手分数
        sumScore.setMatch_id(matchId);
        sumScore.setPlayer_id(playerB_id);
        sumScore.setPlayer_opus(player_opu_b);
        sumScore.setPlayer_name(player_name_b);

        sumScoreTmp = Integer.parseInt(Player_poll_b) + Integer.parseInt(Player_judge_score_b); //计算总分
        sumScore.setSum_score(sumScoreTmp.toString());
        matchMapper.insertSumScore(sumScore);


        result.setCode(200);
        result.setMessage("该比赛统计分数成功，请去排行榜查看");

        return result;
    }

    @Override
    public List<SumScore> getAllSumScoreBySort() {
        List<SumScore> list = matchMapper.selectSumScoreSort();
        for (SumScore item : list) {
            if (item.getScore() == null) {
                item.setScore("0");
            }
            if (item.getPlayer_poll() == null) {
                item.setPlayer_poll("0");
            }
        }

        for (int i = 0; i < list.size() / 2; i++) {
            SumScore item1 = list.get(i * 2);
            SumScore item2 = list.get(i * 2 + 1);

            if (Integer.parseInt(item1.getSum_score()) > Integer.parseInt(item2.getSum_score())) {
                item1.setResult("胜");
                item2.setResult("输");
            } else if (Integer.parseInt(item1.getSum_score()) < Integer.parseInt(item2.getSum_score())) {
                item2.setResult("胜");
                item1.setResult("输");
            } else {
                item1.setResult("平");
                item2.setResult("平");
            }

        }

        return list;
    }
}
