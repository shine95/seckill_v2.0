package com.shine.seckill.exception;

import com.shine.seckill.result.CodeMsg;

/**
 * Create By shine
 * 2018-09-07 13:26
 */

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID=1L;

    private CodeMsg cm;

    public CodeMsg getCm() {
        return cm;
    }

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }
}
