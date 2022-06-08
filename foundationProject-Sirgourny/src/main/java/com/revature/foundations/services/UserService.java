package com.revature.foundations.services;



import com.revature.foundations.daos.UserDaoPostgres;
import com.revature.foundations.dto.ResourceCreationResponse;
import com.revature.foundations.models.User;
import com.revature.foundations.utils.exceptions.InvalidRequestException;

public class UserService {

    private final UserDaoPostgres userDaoPostgres;

    public UserService(UserDaoPostgres userDaoPostgres) {
        this.userDaoPostgres = userDaoPostgres;
    }

    // This is my method
    public ResourceCreationResponse createNewUser(User newUser) {

            // VALIDATE: Validate the data provided from the web layer
            // below: if the user is null
        if (newUser == null ||
           // below: or if the QT is null        // below: or if the QT is an empty string
            newUser.getQuestionText() == null || newUser.getQuestionText().equals("") ||
            // below: or if the AT is null      // below: or if the AT is an empty string
            newUser.getAnswerText() == null || newUser.getAnswerText().equals(""))
        {
            String msg = "Provided user data was invalid. Question and answer text must not be null or empty!";
            // Logger.log(logError())
            throw new InvalidRequestException(msg);
        }

        // VALIDATE: If valid, persist to DB and return its result
       return new ResourceCreationResponse(userDaoPostgres.createUser(newUser).getUser_id());
    }

}
