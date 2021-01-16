    package com.example.CadastroOcorrencias.activity;

    import androidx.appcompat.app.ActionBar;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.cardview.widget.CardView;
    import androidx.core.app.ActivityCompat;

    import android.Manifest;
    import android.app.DatePickerDialog;
    import android.app.TimePickerDialog;
    import android.content.pm.PackageManager;
    import android.database.Cursor;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.Paint;
    import android.graphics.Typeface;
    import android.graphics.pdf.PdfDocument;
    import android.os.Bundle;
    import android.os.Environment;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.DatePicker;
    import android.widget.TextView;
    import android.widget.TimePicker;
    import android.widget.Toast;

    import com.example.CadastroOcorrencias.R;
    import com.example.CadastroOcorrencias.helper.AbordadoDAO;
    import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
    import com.example.CadastroOcorrencias.model.Abordado;
    import com.example.CadastroOcorrencias.model.Acessorios;
    import com.example.CadastroOcorrencias.model.Avaria;
    import com.example.CadastroOcorrencias.model.Equipe;
    import com.example.CadastroOcorrencias.model.Talao;
    import com.example.CadastroOcorrencias.model.Viatura;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;

    public class GerarPdf extends AppCompatActivity {
        String acabamentoInterno = "1";
        Bitmap imagem1,imagem2,imagem3,imagem4,scaledImage1,scaledImage2,scaledImage3,scaledImage4;
        List<Abordado> listaAbordado;
        List<Equipe> listaEquipe;
        List<Acessorios> listaAcessorios;
        CheckBox CheckBoxRiscarRelatorio;
        Viatura viatura = new Viatura();
        List<Avaria> listaAvaria;
        String prelecao;
        List<String> listaPrelecao;
        List<Talao> listaTalao;
        Button btnDataRso,btnInicioTurno,btnFimTurno;
        Button btnGerarRSO,btnGerarCarater;
        CardView cardViewData,cardViewGerarPDF,cardViewInicioTurno,cardViewFimTurno;
        TextView txtDataRSO,txtInicioTurno,txtFimTurno;
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);






        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gerar_pdf);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

            btnGerarRSO = findViewById(R.id.btnGerarRSO);
            btnGerarRSO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPDF();
                }
            });
            CheckBoxRiscarRelatorio = findViewById(R.id.CheckBoxRiscarRelaorio);
            btnDataRso = findViewById(R.id.btnDataRso);
            btnInicioTurno = findViewById(R.id.btnInicioTurno);
            btnInicioTurno.setText(String.format("%02d:%02d",hora,minutos));
            btnFimTurno = findViewById(R.id.btnTerminoTurno);
            btnFimTurno.setText(String.format("%02d:%02d",hora,minutos));

         //--------------------------------------
            cardViewData = findViewById(R.id.cardViewData);
            cardViewInicioTurno = findViewById(R.id.cardView)
         //--------------------------------------


            imagem1 = BitmapFactory.decodeResource(getResources(),R.drawable.pagina_1);
            imagem2 = BitmapFactory.decodeResource(getResources(),R.drawable.pagina_2);
            imagem3 = BitmapFactory.decodeResource(getResources(),R.drawable.pagina_3);
            imagem4 = BitmapFactory.decodeResource(getResources(),R.drawable.pagina_4);
            scaledImage1 = Bitmap.createScaledBitmap(imagem1,967,1361,false);
            scaledImage2 = Bitmap.createScaledBitmap(imagem2,961,1321,false);
            scaledImage3 = Bitmap.createScaledBitmap(imagem3,966,1380,false);
            scaledImage4 = Bitmap.createScaledBitmap(imagem4,961,1385,false);

            listaAbordado = new ArrayList<>();
            listaAbordado = new AbordadoDAO(getApplicationContext()).RecuperarAbordados();
            listaAvaria = new ArrayList<>();
            listaEquipe = new ArrayList<>();
            listaPrelecao = new ArrayList<>();
            listaTalao = new ArrayList<>();
            listaAcessorios = new ArrayList<>();
            CarregarDados();
            btnDataRso.setText(String.format("%02d/%02d/%02d",dia,(mes+1),ano));


            if(!btnDataRso.getText().toString().isEmpty() && !btnInicioTurno.getText().toString().isEmpty() &&!btnFimTurno.getText().toString().isEmpty()){
                btnGerarRSO.setEnabled(true);
            }

            //-----------------------------------------

            cardViewData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(GerarPdf.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            //calendar.set(mYear,mMonth,mDay);
                            // btnDataRso.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                            txtDataRSO.setText(String.format("%02d/%02d/%02d",mDay,(mMonth+1),mYear));

                        }
                    },dia,mes,ano);
                    datePickerDialog.show();
                }
            });
            //-----------------------------------------

            btnDataRso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(GerarPdf.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            //calendar.set(mYear,mMonth,mDay);
                           // btnDataRso.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                            btnDataRso.setText(String.format("%02d/%02d/%02d",mDay,(mMonth+1),mYear));

                        }
                    },dia,mes,ano);
                    datePickerDialog.show();
                }
            });
            btnInicioTurno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TimePickerDialog timePickerDialog = new TimePickerDialog(GerarPdf.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            btnInicioTurno.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();
                }
            });
            btnFimTurno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TimePickerDialog timePickerDialog = new TimePickerDialog(GerarPdf.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            btnFimTurno.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();
                }
            });

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        }

        //Cria PDF RSO
        private void createPDF() {

                    PdfDocument myPdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();

                    PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1100,1500,1).create();
                    PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
                    Canvas canvas = myPage1.getCanvas();
                    canvas.drawBitmap(scaledImage1,50,50,myPaint);

                   myPaint.setTextSize(15);
           


            //Linhas auxiliares
             /*   int x = 50;
                int y = 20;
                myPaint.setStrokeWidth(1);

                myPaint.setTextSize(10);
                myPaint.setTextAlign(Paint.Align.CENTER);
                for(int i = 0;i<96;i++){
                    if(i%2==0){
                        canvas.drawText(String.valueOf(x),x,1400,myPaint);
                        canvas.drawText(String.valueOf(x),x,20,myPaint);
                        canvas.drawLine(x,20,x,1390,myPaint);
                    }else{
                        canvas.drawText(String.valueOf(x),x,1420,myPaint);
                        canvas.drawText(String.valueOf(x),x,10,myPaint);
                        canvas.drawLine(x,10,x,1390,myPaint);
                    }


                    x+=10;
                }

                //linhas horizontais
                for(int i = 0;i<138;i++){
                    canvas.drawText(String.valueOf(y),1020,y+3,myPaint);
                    canvas.drawText(String.valueOf(y),30,y+3,myPaint);
                    canvas.drawLine(40,y,1010,y,myPaint);
                    y+=10;
                }*/

                    //INICIO PREENCHIMENTO
                    myPaint.setTextSize(18);
                    myPaint.setTextAlign(Paint.Align.CENTER);
                   //Data e outras informações
                    int top = 202;
                    canvas.drawText(btnDataRso.getText().toString().substring(0,2),150,top,myPaint);
                    canvas.drawText(btnDataRso.getText().toString().substring(3,5),190,top,myPaint);
                    canvas.drawText(btnDataRso.getText().toString().substring(8),234,top,myPaint);
                    canvas.drawText(viatura.getPrefixo(),450,top,myPaint);
                    canvas.drawText(viatura.getModalidade(),710,top,myPaint);
                    canvas.drawText(viatura.getSetor(),900,top,myPaint);

                    //Informações sobre Inicio do servico e km da vtr
                    //qtr inicio
                    top=230;
                    canvas.drawText(btnInicioTurno.getText().toString(),245,top,myPaint);
                    //qtr Final
                    canvas.drawText(btnFimTurno.getText().toString(),440,top,myPaint);

                    //km inicio e termino e total
                    canvas.drawText(viatura.getKmInicio(),610,top,myPaint);
                    canvas.drawText(viatura.getKmTermino(),785,top,myPaint);
            try{

                int inicio = Integer.parseInt(viatura.getKmInicio().trim());
                int termino = Integer.parseInt(viatura.getKmTermino().trim());

                int rodado = termino-inicio;
                canvas.drawText(String.valueOf(rodado),970,top,myPaint);
            }catch (NumberFormatException e){
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }


                    //Informações sobre a equipe
                    //Encarregado
                    top=255;
                    canvas.drawText(listaEquipe.get(0).getGraduacao(),290,top,myPaint);
                    canvas.drawText(listaEquipe.get(0).getRE(),450,top,myPaint);
                    canvas.drawText(listaEquipe.get(0).getNome(),710,top,myPaint);
                    canvas.drawText(listaEquipe.get(0).getTelefone(),950,top,myPaint);

                    //Motorista
                    top =282;
            canvas.drawText(listaEquipe.get(1).getGraduacao(),290,top,myPaint);
            canvas.drawText(listaEquipe.get(1).getRE(),450,top,myPaint);
            canvas.drawText(listaEquipe.get(1).getNome(),710,top,myPaint);
            canvas.drawText(listaEquipe.get(1).getTelefone(),950,top,myPaint);


            //Auxiliar 1
                    top = 308;
            canvas.drawText(listaEquipe.get(2).getGraduacao(),290,top,myPaint);
            canvas.drawText(listaEquipe.get(2).getRE(),450,top,myPaint);
            canvas.drawText(listaEquipe.get(2).getNome(),710,top,myPaint);
            canvas.drawText(listaEquipe.get(2).getTelefone(),950,top,myPaint);


            //Auxiliar 2
                    top=334;
            canvas.drawText(listaEquipe.get(3).getGraduacao(),290,top,myPaint);
            canvas.drawText(listaEquipe.get(3).getRE(),450,top,myPaint);
            canvas.drawText(listaEquipe.get(3).getNome(),710,top,myPaint);
            canvas.drawText(listaEquipe.get(3).getTelefone(),950,top,myPaint);


                    //informações sobre acessorios
                        myPaint.setTextSize(20);
                        int yPosition = 470;
                        int xPosition = 335;
                        for(int i=0;i<listaAcessorios.size();i++){
                            if(i==12){
                                yPosition=470;
                                xPosition=650;

                            }
                            if(i==24){
                                yPosition=470;
                                xPosition=968;

                            }
                            if(listaAcessorios.get(i).getSim().equals("true")){
                                canvas.drawText("X",xPosition,yPosition,myPaint);
                            }else{
                                canvas.drawText("X",xPosition+30,yPosition,myPaint);

                            }
                            yPosition+=22;
                        }

                    //avarias
                    myPaint.setTextSize(12);
            myPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(listaAvaria.get(0).getDescricao(),82,880,myPaint);
                    canvas.drawText(listaAvaria.get(1).getDescricao(),142,835,myPaint);
                    canvas.drawText(listaAvaria.get(2).getDescricao(),200,830,myPaint);
                    canvas.drawText(listaAvaria.get(3).getDescricao(),242,805,myPaint);
                    canvas.drawText(listaAvaria.get(4).getDescricao(),288,798,myPaint);
                    canvas.drawText(listaAvaria.get(5).getDescricao(),348,790,myPaint);
                    canvas.drawText(listaAvaria.get(6).getDescricao(),385,800,myPaint);
                    canvas.drawText(listaAvaria.get(7).getDescricao(),430,805,myPaint);
                    canvas.drawText(listaAvaria.get(8).getDescricao(),490,820,myPaint);
                    canvas.drawText(listaAvaria.get(9).getDescricao(),515,855,myPaint);
                    canvas.drawText(listaAvaria.get(10).getDescricao(),510,908,myPaint);
                    canvas.drawText(listaAvaria.get(11).getDescricao(),462,948,myPaint);
                    canvas.drawText(listaAvaria.get(12).getDescricao(),340,942,myPaint);
                    canvas.drawText(listaAvaria.get(13).getDescricao(),255,942,myPaint);
                    canvas.drawText(listaAvaria.get(14).getDescricao(),145,945,myPaint);
                    canvas.drawText(listaAvaria.get(15).getDescricao(),85,930,myPaint);


                    canvas.drawText(listaAvaria.get(16).getDescricao(),972,880,myPaint);
                    canvas.drawText(listaAvaria.get(17).getDescricao(),855,830,myPaint);
                    canvas.drawText(listaAvaria.get(18).getDescricao(),770,798,myPaint);
                    canvas.drawText(listaAvaria.get(19).getDescricao(),718,790,myPaint);
                    canvas.drawText(listaAvaria.get(20).getDescricao(),672,800,myPaint);
                    canvas.drawText(listaAvaria.get(21).getDescricao(),548,855,myPaint);
                    canvas.drawText(listaAvaria.get(22).getDescricao(),552,905,myPaint);

                    canvas.drawText(listaAvaria.get(23).getDescricao(),598,945,myPaint);
                    canvas.drawText(listaAvaria.get(24).getDescricao(),715,942,myPaint);
                    canvas.drawText(listaAvaria.get(25).getDescricao(),802,942,myPaint);
                    canvas.drawText(listaAvaria.get(26).getDescricao(),910,945,myPaint);
                    canvas.drawText(listaAvaria.get(27).getDescricao(),968,930,myPaint);




            //Acabamento interno
                    myPaint.setTextSize(20);
/*
                    //switch (acabamentoInterno){
                       // case "1":
                            canvas.drawText("X",710,1048,myPaint);
                          //  break;
                       // case "2":
                            canvas.drawText("X",772,1048,myPaint);
                          //  break;
                        //case "3":
                            canvas.drawText("X",832,1048,myPaint);
                          //  break;
                        //default:
                    //}*/
                    myPaint.setTextSize(20);

                    //Combustivel Assumido
            int positionYCombustivel = 1115;
                    switch (viatura.getCombustivelAssumido()){
                        case "1":
                            canvas.drawText("X",70,positionYCombustivel,myPaint);
                        break;
                        case "2":
                            canvas.drawText("X",110,positionYCombustivel,myPaint);
                            break;
                        case "3":
                            canvas.drawText("X",150,positionYCombustivel,myPaint);
                            break;
                        case "4":
                            canvas.drawText("X",190,positionYCombustivel,myPaint);
                            break;
                        case "5":
                            canvas.drawText("X",230,positionYCombustivel,myPaint);

                            break;
                    }


                    //Combustivel Passado
                     switch (viatura.getCombustivelPassado()){
                case "1":
                    canvas.drawText("X",270,positionYCombustivel,myPaint);
                    break;
                case "2":
                    canvas.drawText("X",310,positionYCombustivel,myPaint);
                    break;
                case "3":
                    canvas.drawText("X",345,positionYCombustivel,myPaint);
                    break;
                case "4":
                    canvas.drawText("X",380,positionYCombustivel,myPaint);
                    break;
                case "5":
                    canvas.drawText("X",420,positionYCombustivel,myPaint);
                    break;
            }

            myPaint.setTextSize(18);
/*
                    //Limpeza da Viatura
                    canvas.drawText("X",520,1113,myPaint);
                    canvas.drawText("X",660,1113,myPaint);
                    canvas.drawText("X",800,1113,myPaint);
                    canvas.drawText("X",940,1113,myPaint);

            myPaint.setTextSize(15);


            canvas.drawText("22:45",280,1310,myPaint);
                    canvas.drawText("Avenida Inocêncio Serafico,1200",520,1310,myPaint);
                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("CFP",720,1310,myPaint);
*/



                    myPdfDocument.finishPage(myPage1);

                    //Pagina 2

                    PdfDocument.Page myPage2 = myPdfDocument.startPage(myPageInfo);
                    canvas = myPage2.getCanvas();
                    myPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawBitmap(scaledImage2,50,50,myPaint);


            //Linhas auxiliares
         /*       int x = 50;
                int y = 20;
                myPaint.setStrokeWidth(1);

                myPaint.setTextSize(10);
                myPaint.setTextAlign(Paint.Align.CENTER);
                for(int i = 0;i<96;i++){
                    if(i%2==0){
                        canvas.drawText(String.valueOf(x),x,1400,myPaint);
                        canvas.drawText(String.valueOf(x),x,20,myPaint);
                        canvas.drawLine(x,20,x,1390,myPaint);
                    }else{
                        canvas.drawText(String.valueOf(x),x,1420,myPaint);
                        canvas.drawText(String.valueOf(x),x,10,myPaint);
                        canvas.drawLine(x,10,x,1390,myPaint);
                    }


                    x+=10;
                }

                //linhas horizontais
                for(int i = 0;i<138;i++){
                    canvas.drawText(String.valueOf(y),1020,y+3,myPaint);
                    canvas.drawText(String.valueOf(y),30,y+3,myPaint);
                    canvas.drawLine(40,y,1010,y,myPaint);
                    y+=10;
                }
*/
                    myPaint.setTextSize(15);

                    //Pessoas Abordadas

            float positionYAbordados =423;
            for(Abordado abordado : listaAbordado) {
                        if (abordado.getNome()!=null) {

                            canvas.drawText(abordado.getNome(), 190, positionYAbordados, myPaint);
                        }
                        if (abordado.getVulgo()!=null) {
                            canvas.drawText(abordado.getVulgo(), 400, positionYAbordados, myPaint);
                        }
                        if (abordado.getRg()!=null) {
                            canvas.drawText(abordado.getRg(), 540, positionYAbordados, myPaint);
                        }
                        if (abordado.getArtigos()!=null) {
                            canvas.drawText(abordado.getArtigos(), 660, positionYAbordados, myPaint);
                        }
                        if (abordado.getEnderecoAbordagem()!=null) {

                            canvas.drawText(abordado.getEnderecoAbordagem(), 825, positionYAbordados, myPaint);
                        }

                        if (abordado.getAtrAbordagem()!=null) {
                            canvas.drawText(abordado.getAtrAbordagem(), 975, positionYAbordados, myPaint);
                        }


                        positionYAbordados += 22.4;


                    }
                    if(CheckBoxRiscarRelatorio.isChecked()){
                        myPaint.setStrokeWidth(2);
                        canvas.drawLine(55,positionYAbordados-21,1005,852,myPaint);
                    }

                    myPaint.setTextSize(15);
                    //Veiculos Abordados
                    float positionYVeiculos = 940;
                    /*for(int i=0;i<10;i++) {
                      canvas.drawText("Honda/Civic",170,positionYVeiculos,myPaint);
                        canvas.drawText("Preto",360,positionYVeiculos,myPaint);
                        canvas.drawText("EPF-2248",480,positionYVeiculos,myPaint);
                        canvas.drawText("36521478",620,positionYVeiculos,myPaint);
                        canvas.drawText("2632541125",790,positionYVeiculos,myPaint);
                        canvas.drawText("Observação",940,positionYVeiculos,myPaint);

                        positionYVeiculos+=22.4;


                    }*/
                    if(CheckBoxRiscarRelatorio.isChecked()){
                        canvas.drawLine(55,positionYVeiculos-22,1005,1368,myPaint);

                    }

                    myPdfDocument.finishPage(myPage2);
                    //Fim da Pagina 2


                    //Inicio Pagina 3

                    PdfDocument.Page myPage3 = myPdfDocument.startPage(myPageInfo);
                    canvas = myPage3.getCanvas();
                    canvas.drawBitmap(scaledImage3,50,50,myPaint);
                    myPaint.setTextSize(20);

            //Linhas auxiliares
             /*  int x = 50;
                int y = 20;
                myPaint.setStrokeWidth(1);

                myPaint.setTextSize(10);
                myPaint.setTextAlign(Paint.Align.CENTER);
                for(int i = 0;i<96;i++){
                    if(i%2==0){
                        canvas.drawText(String.valueOf(x),x,1400,myPaint);
                        canvas.drawText(String.valueOf(x),x,20,myPaint);
                        canvas.drawLine(x,20,x,1390,myPaint);
                    }else{
                        canvas.drawText(String.valueOf(x),x,1420,myPaint);
                        canvas.drawText(String.valueOf(x),x,10,myPaint);
                        canvas.drawLine(x,10,x,1390,myPaint);
                    }


                    x+=10;
                }

                //linhas horizontais
                for(int i = 0;i<138;i++){
                    canvas.drawText(String.valueOf(y),1020,y+3,myPaint);
                    canvas.drawText(String.valueOf(y),30,y+3,myPaint);
                    canvas.drawLine(40,y,1010,y,myPaint);
                    y+=10;
                }
*/

                    //cabecalho pagina 3

                    canvas.drawText(btnDataRso.getText().toString().substring(0,2),180,70,myPaint);
                    canvas.drawText(btnDataRso.getText().toString().substring(3,5),225,70,myPaint);
                    canvas.drawText(btnDataRso.getText().toString().substring(8),270,70,myPaint);
                    canvas.drawText(viatura.getPrefixo(),350,70,myPaint);
                    canvas.drawText(viatura.getModalidade(),540,70,myPaint);
                    canvas.drawText(viatura.getSetor(),690,70,myPaint);
                    canvas.drawText(btnInicioTurno.getText().toString().substring(0,2),810,70,myPaint);
                    canvas.drawText(btnInicioTurno.getText().toString().substring(3,5),850,70,myPaint);
                    canvas.drawText(btnFimTurno.getText().toString().substring(0,2),905,70,myPaint);
                    canvas.drawText(btnFimTurno.getText().toString().substring(3,5),950,70,myPaint);


                    //Taloes
                    float positionYTaloes = 165;
                    for(int i=0;i<listaTalao.size();i++){
                        //1Linha
                        if(listaTalao.get(i).getQtrInicio().length()>4){
                            canvas.drawText(listaTalao.get(i).getQtrInicio().substring(0,2),150,positionYTaloes,myPaint);
                            canvas.drawText(listaTalao.get(i).getQtrInicio().substring(3),180,positionYTaloes,myPaint);
                        }
                        if(listaTalao.get(i).getQtrLocal().length()>4){
                            canvas.drawText(listaTalao.get(i).getQtrLocal().substring(0,2),218,positionYTaloes,myPaint);
                            canvas.drawText(listaTalao.get(i).getQtrLocal().substring(3),245,positionYTaloes,myPaint);
                        }
                        if(listaTalao.get(i).getQtr1Term().length()>4){
                            canvas.drawText(listaTalao.get(i).getQtr1Term().substring(0,2),280,positionYTaloes,myPaint);
                            canvas.drawText(listaTalao.get(i).getQtr1Term().substring(3),310,positionYTaloes,myPaint);
                        }
                        if(listaTalao.get(i).getQtrFinal().length()>4){
                            canvas.drawText(listaTalao.get(i).getQtrFinal().substring(0,2),345,positionYTaloes,myPaint);
                            canvas.drawText(listaTalao.get(i).getQtrFinal().substring(3),375,positionYTaloes,myPaint);
                        }

                        canvas.drawText(listaTalao.get(i).getEndereco(),660,positionYTaloes,myPaint);
                        canvas.drawText(listaTalao.get(i).getNtalao(),960,positionYTaloes,myPaint);

                        //2Linha
                        canvas.drawText(listaTalao.get(i).getKmInicio(),165,positionYTaloes+26,myPaint);
                        canvas.drawText(listaTalao.get(i).getKmLocal(),230,positionYTaloes+26,myPaint);
                        canvas.drawText(listaTalao.get(i).getKm1Term(),295,positionYTaloes+26,myPaint);
                        canvas.drawText(listaTalao.get(i).getKmFinal(),358,positionYTaloes+26,myPaint);
                        canvas.drawText(listaTalao.get(i).getNatureza().substring(5),660,positionYTaloes+26,myPaint);
                        canvas.drawText(listaTalao.get(i).getNatureza().substring(0,3),960,positionYTaloes+26,myPaint);

                        positionYTaloes+=52.7;
                    }
                    //Riscar Relatorio
                    if(CheckBoxRiscarRelatorio.isChecked()){
                        canvas.drawLine(55,positionYTaloes-25,1008,1198,myPaint);

                    }


                    //produividade
                    //1Linha
                    canvas.drawText(String.valueOf(listaAbordado.size()),358,1286,myPaint);
                    canvas.drawText("",670,1288,myPaint);
                    canvas.drawText("",990,1288,myPaint);

                    //7Linha
                    canvas.drawText("",358,1440,myPaint);
                    canvas.drawText("",670,1440,myPaint);
                    canvas.drawText(String.valueOf(listaTalao.size()),990,1420,myPaint);

                    myPdfDocument.finishPage(myPage3);


                    ///Pagina 4
                    PdfDocument.Page myPage4 = myPdfDocument.startPage(myPageInfo);
                    canvas = myPage4.getCanvas();

                    canvas.drawBitmap(scaledImage4,50,50,myPaint);
            //Linhas auxiliares
              /* int x = 50;
                int y = 20;
                myPaint.setStrokeWidth(1);

                myPaint.setTextSize(10);
                myPaint.setTextAlign(Paint.Align.CENTER);
                for(int i = 0;i<96;i++){
                    if(i%2==0){
                        canvas.drawText(String.valueOf(x),x,1400,myPaint);
                        canvas.drawText(String.valueOf(x),x,20,myPaint);
                        canvas.drawLine(x,20,x,1390,myPaint);
                    }else{
                        canvas.drawText(String.valueOf(x),x,1420,myPaint);
                        canvas.drawText(String.valueOf(x),x,10,myPaint);
                        canvas.drawLine(x,10,x,1390,myPaint);
                    }


                    x+=10;
                }

                //linhas horizontais
                for(int i = 0;i<138;i++){
                    canvas.drawText(String.valueOf(y),1020,y+3,myPaint);
                    canvas.drawText(String.valueOf(y),30,y+3,myPaint);
                    canvas.drawLine(40,y,1010,y,myPaint);
                    y+=10;
                }*/

                    myPaint.setTextSize(20);

                   myPaint.setTextAlign(Paint.Align.LEFT);
            String prelecao2 = prelecao.replace("*"," *");
            String[] cabecalhoSeparado= prelecao2.split(" ");
            String valor2 = "";
            float topo = 135;


            ///Gerar Lista de palavras
            for (int i = 0;i<cabecalhoSeparado.length;i++){

                if(valor2.length()<100 && valor2.length()+cabecalhoSeparado[i].trim().length()<100) {

                    if (cabecalhoSeparado[i].trim().length() > 3) {
                        if (cabecalhoSeparado[i].contains("*")) {
                            String  palavra = cabecalhoSeparado[i].replace("*", "");

                            if (!valor2.isEmpty()) {
                                listaPrelecao.add(valor2);
                                valor2 = "";
                                valor2+=palavra+" ";
                                continue;
                            }
                        }
                    }

                    valor2 += cabecalhoSeparado[i].trim() + " ";

                }else{
                    listaPrelecao.add(valor2);
                    valor2 = "";
                    valor2+=cabecalhoSeparado[i].trim()+" ";
                }

            }
            listaPrelecao.add(valor2);


            for(int i=0;i<listaPrelecao.size();i++){
                canvas.drawText(listaPrelecao.get(i),65,topo,myPaint);
                topo+=38.5;
            }

            ///data no final do Relatorio
            myPaint.setTextAlign(Paint.Align.CENTER);

            canvas.drawText(btnDataRso.getText().toString().substring(0,2),195,1305,myPaint);
            String mesAtual = "";
            switch (mes){
                case 0:
                mesAtual="Janeiro";
                break;
                case 1:
                    mesAtual="Fevereiro";
                    break;
                case 2:
                    mesAtual="Março";
                    break;
                case 3:
                    mesAtual="Abril";
                    break;
                case 4:
                    mesAtual="Maio";
                    break;
                case 5:
                    mesAtual="Junho";
                    break;
                 case 6:
                    mesAtual="Julho";
                    break;
                case 7:
                    mesAtual="Agosto";
                    break;
                case 8:
                    mesAtual="Setembro";
                    break;

                case 9:
                    mesAtual="Outubro";
                    break;
                case 10:
                    mesAtual="Novembro";
                    break;
                case 11:
                    mesAtual="Dezembro";
                    break;

                default:
                    mesAtual="";

            }
            canvas.drawText(mesAtual,290,1305,myPaint);
            canvas.drawText(String.valueOf(ano),400,1305,myPaint);


            if(CheckBoxRiscarRelatorio.isChecked()){
                canvas.drawLine(58,topo-35,1008,1255,myPaint);

            }
            myPdfDocument.finishPage(myPage4);

            Calendar calendar = Calendar.getInstance();
            int dia2 = calendar.get(Calendar.DAY_OF_MONTH);
            int mes2 = calendar.get(Calendar.MONTH)+1;
            int ano2 = calendar.get(Calendar.YEAR);
            int hora2 = calendar.get(Calendar.HOUR_OF_DAY);
            int minutos2 = calendar.get(Calendar.MINUTE);

            //String data= String.format("%02d%02d%02d_%02d%02d",dia2,mes2,ano2,hora2,minutos2);

            String data= String.format("%02d%02d%02d",dia2,mes2,ano2);
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

                    String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/RSO_"+data+".pdf";
                    File file = new File(filePath);

                    try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                    }catch (IOException e){
                        e.printStackTrace();
                    }


                    myPdfDocument.close();
                    Toast.makeText(GerarPdf.this, "Documento criado com sucesso", Toast.LENGTH_SHORT).show();
                }

                //Cria PDF Lista de Carater

        public void CarregarDados() {
            MyDatabaseHelper myDb = new MyDatabaseHelper(this);
            Cursor cursor = myDb.readAllData("Equipe", "T");

            while (cursor.moveToNext()) {
                Equipe equipe = new Equipe();
                equipe.setId(cursor.getInt(0));
                equipe.setGraduacao(cursor.getString(1));
                equipe.setRE(cursor.getString(2));
                equipe.setNome(cursor.getString(3));
                equipe.setTelefone(cursor.getString(4));
                equipe.setFuncao(cursor.getString(5));
                listaEquipe.add(equipe);
            }


            cursor = myDb.readAllData("Acessorios", "T");

            while (cursor.moveToNext()) {
                Acessorios acessorios = new Acessorios();
                acessorios.setDescricao(cursor.getString(1));
                acessorios.setSim(cursor.getString(2));

                listaAcessorios.add(acessorios);
            }


            cursor = myDb.readAllData("Viatura", "VTR");

            while (cursor.moveToNext()) {
                viatura.set_id(cursor.getInt(0));
                viatura.setPrefixo(cursor.getString(1));
                viatura.setModalidade(cursor.getString(2));
                viatura.setSetor(cursor.getString(3));
                viatura.setKmInicio(cursor.getString(4));
                viatura.setKmTermino(cursor.getString(5));
                viatura.setCombustivelAssumido(cursor.getString(6));
                viatura.setCombustivelPassado(cursor.getString(7));
                viatura.setAcabamentoInterno(cursor.getString(8));
                viatura.setObs(cursor.getString(9));
            }


            cursor = myDb.readAllData("Avarias", "T");

            while (cursor.moveToNext()) {
                Avaria avaria = new Avaria();
                avaria.set_id(cursor.getInt(0));
                avaria.setDescricao(cursor.getString(1));
                listaAvaria.add(avaria);
            }

            cursor = myDb.readAllData("Relatorio", "RSO");
            while (cursor.moveToNext()) {
                prelecao = cursor.getString(1);
            }


            cursor = myDb.readAllData("Taloes", "TR");
            while (cursor.moveToNext()) {
                Talao talao = new Talao();
                talao.setId(cursor.getInt(0));
                talao.setNtalao(cursor.getString(1));
                talao.setQtrInicio(cursor.getString(2));
                talao.setQtrLocal(cursor.getString(3));
                talao.setQtr1Term(cursor.getString(4));
                talao.setQtrFinal(cursor.getString(5));

                talao.setKmInicio(cursor.getString(6));
                talao.setKmLocal(cursor.getString(7));
                talao.setKm1Term(cursor.getString(8));
                talao.setKmFinal(cursor.getString(9));
                talao.setEndereco(cursor.getString(10));
                talao.setNatureza(cursor.getString(11));
                talao.setData(cursor.getString(12));
                talao.setResultado(cursor.getString(13));
                talao.setObservacao(cursor.getString(14));
                talao.setArquivado(cursor.getString(15));

                listaTalao.add(talao);

            }
        }

    }