/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：Apr 8, 2016 4:17:41 PM 
 */
package com.turingcat.mybatis;

import com.turingcat.mybatis.Question;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */

public interface ISiriDao {


    Object get(Question question);

	int save(Question question);
}
