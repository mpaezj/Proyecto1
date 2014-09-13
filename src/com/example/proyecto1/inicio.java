package com.example.proyecto1;


import java.util.List;



import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class inicio extends Fragment {
	private Button botonagregar ;
	private Button botoncalcular;
	private agregarmateria agregarmateria;
	List<materias> listmat;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.inicial, container, false);
		
		//----
		
		 botonagregar = (Button) rootView.findViewById(R.id.button1);
		 botoncalcular = (Button) rootView.findViewById(R.id.button2);

		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		list.setClickable(true);
		
		helper helper= OpenHelperManager.getHelper(rootView.getContext(),helper.class);
		RuntimeExceptionDao<materias, String> materiasDao = helper.getMateriaRuntimeDao();
		listmat = materiasDao.queryForAll();
		
		//Toast.makeText(rootView.getContext(), listmat.get(0).getNombre(), Toast.LENGTH_SHORT).show();
		adamaterias ada = new adamaterias(rootView.getContext(), listmat);
		list.setAdapter(ada);
		
		
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long index) {
				trajenota trajenota = new trajenota();
				Bundle args = new Bundle();
				args.putString("nombre", listmat.get(position).getNombre());
				args.putInt("creditos", listmat.get(position).getCreditos());
				args.putDouble("nota", listmat.get(position).getNota());
				trajenota.setArguments(args);
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, trajenota).addToBackStack("trajenota")
						.commit();
				
				
			}
		});
		botonagregar.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				agregarmateria = new agregarmateria();
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, agregarmateria).addToBackStack("agregarmateria")
						.commit();
			}
		});
		return rootView;
	}

}
