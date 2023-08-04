package com.cq.edu.dao;

import com.cq.edu.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MatchMapper {
    List<Match> selectAllMatchInfo();

    int insertAutoGenerateMatch(Match match);

    List<Singer> selectAllSingerByState(String state);

    List<SumScore> selectSumScoreSort();

    int updateSingerState(@Param("id")String id, @Param("take_state")String take_state);

    int updateMatchState(@Param("matchId")String matchId, @Param("state")String state);

    int updateMatchPoll(@Param("player_id")String player_id, @Param("match_id")String match_id);

    Match selectMatchState(@Param("matchId")String matchId);

    VoteDetails selectVoteDetails(@Param("matchId")String matchId,
                                  @Param("playerA_id")String playerA_id, @Param("playerB_id")String playerB_id);

    List<Score> selectScore(@Param("matchId")String matchId,
                            @Param("playerA_id")String playerA_id, @Param("playerB_id")String playerB_id);

    SumScore selectSumScoreExist(@Param("match_id")String match_id, @Param("player_id")String player_id);

    SumScore selectSumScore(@Param("match_id")String match_id, @Param("player_id")String player_id);

    int insertSumScore(SumScore sumScore);

    int insertVoteCountRecordByTick(VoteCount voteCount);

    VoteCount selectVoteCountRecordByTick(@Param("matchId")String matchId);

    VoteDetails selectLockTable(@Param("player_id")String player_id, @Param("match_id")String match_id);

    int insertVoteDetails(Match match, @Param("player_id")String player_id);

}
