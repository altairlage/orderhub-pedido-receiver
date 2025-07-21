package com.fiap.orderhub.exceptions;

import br.com.orderhub.core.exceptions.OrderhubException;

public class ProduceOnQueueException extends OrderhubException {
    public ProduceOnQueueException(String message) {
        super(message);
    }
}
