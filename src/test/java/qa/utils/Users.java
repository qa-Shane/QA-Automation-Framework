package qa.utils;

import lombok.Getter;

@Getter
public enum Users {
    STANDARD("standard_user"), LOCKED("locked_out_user"), PROBLEM("problem_user"), GLITCH("performance_glitch_user"),
    ERROR("error_user"), VISUAL("visual_user");

    private final String username;

    Users(String username){
        this.username = username;
    }
}
