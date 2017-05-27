package kr.co.midas.midasmobile.base.callback;

import kr.co.midas.midasmobile.base.domain.User;

public interface LoginCallback {

    void onSuccess(User user);

    void onFailure(Throwable throwable);

}
