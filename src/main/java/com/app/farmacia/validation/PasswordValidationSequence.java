package com.app.farmacia.validation;

import javax.validation.GroupSequence;

@GroupSequence({ValidationNotEmpty.class, ValidationSize.class, ValidationPasswordMatches.class})
public interface PasswordValidationSequence {
}
