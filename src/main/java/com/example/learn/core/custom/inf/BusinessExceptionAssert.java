package com.example.learn.core.custom.inf;

import java.text.MessageFormat;

import com.example.learn.core.custom.expclass.BaseException;
import com.example.learn.core.custom.expclass.BusinessException;

public interface BusinessExceptionAssert extends IResponseEnum, Assert{

	@Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }
	
}
