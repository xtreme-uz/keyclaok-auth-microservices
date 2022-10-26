package uz.xtreme.userprovider;

import lombok.Data;

@Data
public class VerifyPasswordResponse {
    private boolean verified;

    public static VerifyPasswordResponse of(boolean result) {
        VerifyPasswordResponse verifyPasswordResponse = new VerifyPasswordResponse();
        verifyPasswordResponse.setVerified(result);
        return verifyPasswordResponse;
    }
}
