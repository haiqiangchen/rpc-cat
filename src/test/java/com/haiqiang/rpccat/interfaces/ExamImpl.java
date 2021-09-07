package com.haiqiang.rpccat.interfaces;

import com.haiqiang.rpccat.Exam;
import com.haiqiang.rpccat.annotation.Service;

/**
 * @author haiqiang
 * @date 2021/9/7 16:01
 */
@Service(Exam.class)
public class ExamImpl implements Exam{
    @Override
    public Object hello(String name) {
        return "hello "+name+" go here";
    }
}
