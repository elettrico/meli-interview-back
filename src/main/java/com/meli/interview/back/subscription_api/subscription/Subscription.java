package com.meli.interview.back.subscription_api.subscription;

public class Subscription {
    private String partner;

    /**
     * devuelve el precio correspondiente a la subscription
     * @return
     * @throws IllegalArgumentException
     */
    public float getPrice() throws IllegalArgumentException {
        if (partner == null) throw new IllegalArgumentException("Subscription nula");
        float price = 0;
        switch(partner) {
            case "disney":
            price = 100;
            break;
            case "netflix":
            price = 200;
            break;
            case "spotify":
            price = 50;
            break;
            default:
            throw new IllegalArgumentException("Subscription no reconocida");
        }
        return price;
     }
}
