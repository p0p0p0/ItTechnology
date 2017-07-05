package com.safetyhuge.chanlian.safetyhuge.utils;

import java.util.ArrayList;

/**
 * 作者：王海宾 on 2017/4/17 0017 14:45
 * 邮箱：995696826@qq.com
 */

public class MyList extends ArrayList {
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.size(); i++) {
            if (i == 0) {
                string = "[\"" + i + "\",\"" + this.get(i) + "\"]";
            } else if (i == this.size() - 1) {
                string = string + ",\"" + i + "\",\"" + this.get(i) + "\"]";
            } else {
                string = string + ",\"" + i + "\",\"" + this.get(i) + "\"]";
            }

        }

        return string;
    }
}