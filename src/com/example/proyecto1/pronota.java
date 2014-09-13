package com.example.proyecto1;

import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class pronota extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.deseadomateria, container, false);
		
		String n = getArguments().getString("nombre");
		int c = getArguments().getInt("credtios");
		double nt = getArguments().getDouble("nota");
		double deseado = getArguments().getDouble("deseadda");
		materias materia = new materias(n,c);
		materia.setNota(nt);
		helper helper= OpenHelperManager.getHelper(rootView.getContext(),helper.class);
		RuntimeExceptionDao<notas, String> notasDao = helper.getNotasRuntimeDao();
		List<notas> listnot = notasDao.queryForEq("nombremateria_id", materia);
		
		
		return rootView;
	}
}
