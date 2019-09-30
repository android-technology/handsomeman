package com.tt.handsomeman.response;

public class LoginResponse extends StandardResponse {
    private TokenState data;

    public TokenState getData() {
        return data;
    }

    public void setData(TokenState data) {
        this.data = data;
    }
}
