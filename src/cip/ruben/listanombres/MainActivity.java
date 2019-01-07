package cip.ruben.listanombres;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView lista;
	private ArrayList<String> valores;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		valores = new ArrayList<String>();
		valores.add("Antonio Dominguez Ávila");
		valores.add("Rubén Plasencia Galván");
		valores.add("Raul Palmero Ramos");
		

		lista = (ListView) findViewById(R.id.listaOpciones);
		registerForContextMenu(lista);
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, valores);

		lista.setAdapter(adapter);
		lista.setTextFilterEnabled(true);

		/*lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final int posicion = position;

				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(
						MainActivity.this);
				dialogo1.setTitle("Importante");
				dialogo1.setMessage("¿ Elimina este nombre ?");
				dialogo1.setCancelable(false);
				dialogo1.setPositiveButton("Confirmar", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						valores.remove(posicion);
						adapter.notifyDataSetChanged();
						Toast.makeText(getApplicationContext(),
								"Se ha eliminado", Toast.LENGTH_SHORT).show();

					}
				});
				dialogo1.setNegativeButton("Cancelar", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
				dialogo1.show();
				return false;
			}

		});*/

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.borrar:
			valores.remove(valores.get((int) info.id));
			adapter.notifyDataSetChanged();
			Toast.makeText(getApplicationContext(), "Borrado", Toast.LENGTH_SHORT).show();
			return true;
		default:
		return super.onContextItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void vistaAñadir() {
		final AlertDialog dialogBuilder = new AlertDialog.Builder(this)
				.create();
		LayoutInflater inflater = this.getLayoutInflater();
		View vista = inflater.inflate(R.layout.user_input_dalog_box, null);

		final EditText et_nombre =(EditText) vista.findViewById(R.id.et_nombre);
		Button b_añadir= (Button) vista.findViewById(R.id.b_add);
		Button b_cancelar = (Button) vista.findViewById(R.id.b_cancelar);

		b_añadir.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				valores.add(et_nombre.getText().toString());
				Toast.makeText(getApplicationContext(), "Añadido", Toast.LENGTH_SHORT).show();
				adapter.notifyDataSetChanged();
				dialogBuilder.dismiss();
				

			}
		});
		b_cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogBuilder.dismiss();
			}
		});

		dialogBuilder.setView(vista);
		dialogBuilder.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.nombreNuevo) {
			vistaAñadir();


		}
		return super.onOptionsItemSelected(item);
	}
}
