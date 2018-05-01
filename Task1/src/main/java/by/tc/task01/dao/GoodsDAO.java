package by.tc.task01.dao;

import by.tc.task01.entity.Sellable;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.exception.TaskException;

import java.util.List;

public interface GoodsDAO {
    <E> List<Sellable> find(Criteria<E> criteria) throws TaskException;
}
