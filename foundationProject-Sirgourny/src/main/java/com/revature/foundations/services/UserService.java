package com.revature.foundations.services;

import com.revature.foundations.dto.ResourceCreationResponse;
import com.revature.foundations.models.AppUser;
import com.revature.foundations.models.User;
import javafx.scene.canvas.GraphicsContext;

public class UserService {

    private GraphicsContext userDAO;

    public User createUser(User newUser) throws IvalidRequestException {

        if (newUser == null ||
            newUser.getQuestionText() == null || newUser.getQuestionText().equals("") ||
            newUser.getAnswerText() == null || newUser.getAnswerText().equals(""))
        {
            String msg = "Provided user data was invalid. Question and answer text must not be null or empty!";
            // Logger.log(msg, LogLevel.ERROR);
            throw new IvalidRequestException(msg);
        }

        // If valid, persist to DB and return its result
        return new ResourceCreationResponse(userDAO.save(newUser).getId());

    }

        }
    }
}
