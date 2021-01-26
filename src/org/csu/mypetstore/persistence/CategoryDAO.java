package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDAO {

    //获取所有类列表
    List<Category> getCategoryList();

    //根据类ID查找类
    Category getCategory(String categoryId);

}
