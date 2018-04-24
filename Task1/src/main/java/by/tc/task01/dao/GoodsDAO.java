package by.tc.task01.dao;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.exception.TaskException;

import java.util.List;

public interface GoodsDAO {
    <E> List<Goods> find(Criteria<E> criteria) throws TaskException;
}
