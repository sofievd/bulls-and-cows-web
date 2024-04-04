package se.vandingenen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;

@Service
@SessionScope
public class GameService{
    @Autowired
    BullsAndCowsRules rules;


}