package com.yangztel.lbase.base;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public abstract class BaseModel implements IModel{
    public BusCallBack callBack;

    public BaseModel(BusCallBack busCallBack){
        this.callBack = busCallBack;
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dissDialog() {

    }
    public interface BusCallBack{
        void busCallBack(BusBean busBean);
    }
}
