package com.example.robacobres_androidclient.callbacks;

import com.example.robacobres_androidclient.models.Question;

public interface QuestionCallback {
    void onSubmittedOK(Question q);
    void onSubmittedError();
}
