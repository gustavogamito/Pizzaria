package com.example.logonrm.pizzaria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.logonrm.pizzaria.model.Pedido;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvCliente)
    TextView tvCliente;

    @BindView(R.id.cbAtum)
    CheckBox cbAtum;

    @BindView(R.id.cbBacon)
    CheckBox cbBacon;

    @BindView(R.id.rgTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    private String nomeUsuario;

    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent() != null){
            nomeUsuario = getIntent().getStringExtra("USUARIO");
        }
        tvCliente.setText(nomeUsuario);

        setListenerCheckBox(cbAtum);
        setListenerCheckBox(cbBacon);
    }

    private void setListenerCheckBox(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pedido.addSabor(checkBox.getText().toString());
                }else {
                    pedido.removeSabor(checkBox.getText().toString());
                }
            }
        });
    }

    @OnClick(R.id.btFecharPedido)
    public void fecharPedido(){

        pedido.setCliente(nomeUsuario);
        pedido.setTamanho(getTamanhoSelecionado());
        pedido.setFormaPagamento(spPagamentos.getSelectedItem().toString());

        Intent i = new Intent(this, ConfirmarPedidoActivity.class);
        i.putExtra("PEDIDO", pedido);
        startActivity(i);
    }

    public String getTamanhoSelecionado() {
        return ((RadioButton)findViewById(R.id.rbBroto)).getText().toString();
    }
}