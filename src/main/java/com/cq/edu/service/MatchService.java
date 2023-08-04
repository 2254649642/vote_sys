package com.cq.edu.service;

import com.cq.edu.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchService {
    List<Match> findAllMatch();

    Result autoGenerateMatch(String matchNumber);

    List<Singer> getAllSingerByState(String state);

    int updateSingerState(String id, String take_state);

    int updateMatchPoll(String player_id, String match_id);

    Result updateMatchState(String matchId, String state);

    Result stopMatchState(String matchId, String state);

    Match getMatchStateById(String matchId);

    List<SumScore> getAllSumScoreBySort();

    Result<VoteDetails> getVoteDetailsByMatchId(String matchId, String playerA_id, String playerB_id);

    Result addVoteCountRecordByTick(VoteCount voteCount);

    VoteCount getVoteCountRecordByTick(String matchId);

    Result addSumScore(String matchId, String playerA_id, String playerB_id);



}
