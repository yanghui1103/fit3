package com.bw.fit.common.data.source.impl;

import org.springframework.stereotype.Component;

import com.bw.fit.common.data.source.NoSQLDataSource;

@Component
public class NoSQLDataSourceImpl implements NoSQLDataSource {

	@Override
	public void del_list(String listName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lPush_list(String listName, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lrange(String listName, int start, int end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rPush(String listName, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lRemove(String listName, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lIndex(String listName, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lPop(String listName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rPop(String listName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sAdd(String setName, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sRemove(String setName, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sCard(String setName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean sisMember(String setName, Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
