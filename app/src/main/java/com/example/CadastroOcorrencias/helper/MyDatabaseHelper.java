package com.example.CadastroOcorrencias.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.CadastroOcorrencias.activity.Equipe_Inicial;
import com.example.CadastroOcorrencias.defaultConfig.defaultConfig;
import com.example.CadastroOcorrencias.model.Acessorios;
import com.example.CadastroOcorrencias.model.Avaria;
import com.example.CadastroOcorrencias.model.Carater;
import com.example.CadastroOcorrencias.model.Equipe;
import com.example.CadastroOcorrencias.model.Viatura;

import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "CadastroTalao.db";
    private static final  int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Taloes";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NTALAO = "ntalao";
    public static final String COLUNA_QTR_INICIO = "qtr_inicio";
    public static final String COLUNA_QTR_LOCAL = "qtr_local";
    public static final String COLUNA_QTR_1TERM = "qtr_1term";
    public static final String COLUNA_QTR_FINAL = "qtr_final";
    public static final String COLUNA_KM_INICIO = "km_inicio";
    public static final String COLUNA_KM_LOCAL = "km_local";
    public static final String COLUNA_KM_1TERM = "km_1term";
    public static final String COLUNA_KM_FINAL = "km_final";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_NATUREZA = "natureza";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_RESULTADO = "resultado";
    public static final String COLUNA_OBESERVACAO  ="observacao";
    public static final String COLUNA_ARQUIVADO = "arquivado";

     SQLiteDatabase read = getReadableDatabase();
    SQLiteDatabase write = getWritableDatabase();




    public MyDatabaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //CRIAR A TABELA DO BANCO DE DADOS
    @Override
    public void onCreate(SQLiteDatabase db) {

        //String para criação das tabelas
        String query = "CREATE TABLE "+ TABLE_NAME +
                " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NTALAO + " TEXT, " +
                COLUNA_QTR_INICIO + " TEXT," +
                COLUNA_QTR_LOCAL + " TEXT," +
                COLUNA_QTR_1TERM + " TEXT," +
                COLUNA_QTR_FINAL + " TEXT, " +
                COLUNA_KM_INICIO + " TEXT, " +
                COLUNA_KM_LOCAL + " TEXT, " +
                COLUNA_KM_1TERM + " TEXT, " +
                COLUNA_KM_FINAL + " TEXT, " +
                COLUNA_ENDERECO + " TEXT, " +
                COLUNA_NATUREZA + " TEXT, " +
                COLUNA_DATA + " TEXT, " +
                COLUNA_RESULTADO + " TEXT, " +
                COLUNA_OBESERVACAO +" TEXT, " +
                COLUNA_ARQUIVADO + " TEXT )" ;

        String queryTableEnderecos = "CREATE TABLE Enderecos(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT)";
        String queryTableAbordados = "CREATE TABLE Abordados(_id INTEGER PRIMARY KEY AUTOINCREMENT,foto BLOB,nome TEXT,rg TEXT,vulgo TEXT,enderecoAbordagem TEXT," +
                "qtrAbordagem TEXT,dataAbordagem TEXT,artigos TEXT,tatuagens TEXT,tatoo1 BLOB,tatoo2 BLOB,tatoo3 BLOB,tatoo4 BLOB,tatoo5 BLOB,tatoo6 BLOB,Observacao TEXT )";
        String queryTableNatureza = "CREATE TABLE ListaNatureza( _id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT)";
        String queryTableResultado = "CREATE TABLE ListaResultado(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT)";
        String queryTableRelatorio  = "CREATE TABLE Relatorio(_id INTEGER PRIMARY KEY,texto TEXT)";
        String queryTableEquipe = "CREATE TABLE Equipe(_id INTEGER PRIMARY KEY, Graduacao TEXT,RE TEXT, Nome TEXT, Telefone TEXT,Funcao TEXT)";
        String queryTableAcessorios = "CREATE TABLE Acessorios(_id INTEGER PRIMARY KEY AUTOINCREMENT,Descricao TEXT, Sim TEXT, Categoria TEXT)";

        String queryTableViatura = "CREATE TABLE Viatura(_id INTEGER PRIMARY KEY ,Prefixo TEXT, Modalidade TEXT, Setor TEXT,kmInicio TEXT,kmFinal TEXT,CombustivelAssumido TEXT,CombustivelPassado TEXT,AcabamentoInterno TEXT,Obs TEXT)";

        String queryTableAvarias = "CREATE TABLE Avarias(_id INTEGER PRIMARY KEY AUTOINCREMENT,Avaria TEXT)";
        String queryTableCarater = "CREATE TABLE Carater(_id INTEGER PRIMARY KEY AUTOINCREMENT,Numeros TEXT, Letras TEXT, Modelo TEXT,Cor TEXT,Ano TEXT,Area TEXT,Natureza TEXT)";


        //Criação das Tabelas
        db.execSQL(query);
        db.execSQL(queryTableEnderecos);
        db.execSQL(queryTableAbordados);
        db.execSQL(queryTableNatureza);
        db.execSQL(queryTableResultado);
        db.execSQL(queryTableAcessorios);

        /* Cria Tabela Viatura e insere Dados de 1 viatura */
        db.execSQL(queryTableViatura);
        ContentValues cViatura = new ContentValues();
        cViatura.put("_id",1);
        cViatura.put("Prefixo","");
        cViatura.put("Modalidade","");
        cViatura.put("Setor","");
        cViatura.put("kmInicio","");
        cViatura.put("kmFinal","");
        cViatura.put("CombustivelAssumido","");
        cViatura.put("CombustivelPassado","");
        cViatura.put("AcabamentoInterno","");
        cViatura.put("Obs","");
        db.insert("Viatura",null,cViatura);



        //Cria Table e preenche com dados padrão
        db.execSQL(queryTableAvarias);
        for(int i=0;i<28;i++){
            ContentValues cv = new ContentValues();
            cv.put("Avaria","");
            db.insert("Avarias",null,cv);
        }


        //Criar e preencher tabela de equipe com valores padrao
        db.execSQL(queryTableEquipe);

        //Preenche a tabela de Natureza padrao
        defaultConfig defaultConfig = new defaultConfig();
        String[] listaNatureza = defaultConfig.ListaNatureza();
        String[] listaResultado = defaultConfig.listaResultado();
        String[] listaAcessorios = defaultConfig.ListaAcessorios();
        String[] listaFuncionamento = defaultConfig.ListaFuncionamento();
        List<Equipe> listaEquipe = defaultConfig.ListaEquipe();


        //Dados Da equipe
        for(Equipe e:listaEquipe){
            ContentValues cv = new ContentValues();
            cv.put("_id",e.getId());
            cv.put("Graduacao",e.getGraduacao());
            cv.put("RE",e.getRE());
            cv.put("Nome",e.getNome());
            cv.put("Telefone",e.getTelefone());
            cv.put("Funcao",e.getFuncao());
            db.insert("Equipe",null,cv);
        }

        //Criar tabela relatorio e preenche com um valor
        db.execSQL(queryTableRelatorio);
        ContentValues cvR = new ContentValues();
        cvR.put("_id",1);
        cvR.put("texto","");
        db.insert("Relatorio",null,cvR);

        //Lista de Ntureza
        for (String s : listaNatureza) {
            ContentValues cv = new ContentValues();
            cv.put("nome", s);
            db.insert("ListaNatureza", null, cv);
        }
        //Lista de resultado
        for (String s : listaNatureza) {
            ContentValues cv = new ContentValues();
            cv.put("nome", s);
            db.insert("ListaResultado", null, cv);
        }

        //Lista de Acessorios
        for (String s : listaAcessorios) {
            ContentValues cv = new ContentValues();
            cv.put("Descricao", s);
            cv.put("Sim","");
            cv.put("Categoria","Acessorio");
            db.insert("Acessorios", null, cv);
        }
        //Lista De Funcionamento
        for (String s : listaFuncionamento) {
            ContentValues cv = new ContentValues();
            cv.put("Descricao", s);
            cv.put("Sim","");
            cv.put("Categoria","Funcionamento");
            db.insert("Acessorios", null, cv);
        }

        db.execSQL(queryTableCarater);
        for(int i=0;i<15;i++){
            ContentValues cv = new ContentValues();
            cv.put("Numeros","");
            cv.put("Letras","");
            cv.put("Modelo","");
            cv.put("Cor","");
            cv.put("Ano","");
            cv.put("Area","");
            cv.put("Natureza","");
            db.insert("Carater",null,cv);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Enderecos");
        db.execSQL("DROP TABLE IF EXISTS Abordados");
        db.execSQL("DROP TABLE IF EXISTS ListaNatureza");
        db.execSQL("DROP TABLE IF EXISTS ListaResultado");
        db.execSQL("DROP TABLE IF EXISTS Relatorio");
        db.execSQL("DROP TABLE IF EXISTS Equipe");
        db.execSQL("DROP TABLE IF EXISTS Acessorios");
        db.execSQL("DROP TABLE IF EXISTS Viatura");


        onCreate(db);
    }



    public  Cursor readAllData(String tableName,String v){
        String query =null;
        if(v=="A"){
            query = "SELECT * FROM " + tableName + " where arquivado = 'Ativo' order by _id desc";
        }
        if(v=="TR"){
            query = "SELECT * FROM " + tableName + " where arquivado = 'Ativo'";
        }
        if(v=="I"){
            query = "SELECT * FROM " + tableName + " where arquivado = 'Arquivado'";
        }
        if(v=="E"){
            query = "SELECT * FROM " + tableName + " order by nome";
        }
        if(v=="N"){
            query = "SELECT * FROM " + tableName + " order by nome";
        }
        if(v=="R"){
            query = "SELECT * FROM " + tableName + " order by nome";
        }
        if(v=="RSO"){
            query = "SELECT * FROM " + tableName;
        }
        if(v=="T"){
            query = "SELECT * FROM " + tableName;
        }
        if(v=="AC"){
            query = "SELECT * FROM " + tableName + " where Categoria = 'Acessorio'";

        }
        if(v=="FC"){
            query = "SELECT * FROM " + tableName + " where Categoria = 'Funcionamento'";
        }
        if(v=="VTR"){
            query = "SELECT * FROM " + tableName;
        }




       // SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = null;
        if(read != null){
            cursor = read.rawQuery(query,null);
        }
        return  cursor;
    }



    //metodo para excluir apeas 1 registro
    public void deleteOneRow(String row_id,String tableName){
        //SQLiteDatabase db = this.getWritableDatabase();
        long resultado = write.delete(tableName,"_id=?",new String[]{row_id});
        if(resultado== -1){
            Toast.makeText(context,"Erro ao deletar.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Deletado com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }

    //metodo para excuir todos os dados
    public void deleteAllData(String tableName){
        //SQLiteDatabase db = this.getWritableDatabase();
       write.execSQL("DELETE FROM "+ tableName);
    }

    ///metodos para adicionar enderecos
    public void adicionarMisto(String valorMisto,String tableName){
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",valorMisto);

        long resultado = write.insert(tableName,null,cv);
        if(resultado== -1){
            Toast.makeText(context,"Erro ao cadastrar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adicionado com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }


    public void atualizarRelatorio(String texto,String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("texto", texto);

        long retorno = db.update("Relatorio", cv, "_id=?", new String[]{_id});

        if (retorno == -1) {
            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Dados atualizados.", Toast.LENGTH_SHORT).show();

        }


    }

    public void atualizarEquipe(Equipe equipe, String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("Graduacao",equipe.getGraduacao());
        cv.put("RE",equipe.getRE());
        cv.put("Nome",equipe.getNome());
        cv.put("Telefone",equipe.getTelefone());



        long retorno = db.update("Equipe", cv, "_id=?", new String[]{_id});

        if (retorno == -1) {
            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Dados atualizados.", Toast.LENGTH_SHORT).show();

        }


    }

    public void atualizarAcessorios(Acessorios acessorios, String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("Sim",acessorios.getSim());


        long retorno = db.update("Acessorios", cv, "_id=?", new String[]{_id});

        if (retorno == -1) {
            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        }


    }

    public void atualizarViatura(Viatura viatura, String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("Prefixo",viatura.getPrefixo());
        cv.put("Modalidade",viatura.getModalidade());
        cv.put("Setor",viatura.getSetor());
        cv.put("kmInicio",viatura.getKmInicio());
        cv.put("kmFinal",viatura.getKmTermino());
        cv.put("CombustivelAssumido",viatura.getCombustivelAssumido());
        cv.put("CombustivelPassado",viatura.getCombustivelPassado());
        cv.put("AcabamentoInterno",viatura.getAcabamentoInterno());
        cv.put("Obs",viatura.getObs());


        long retorno = db.update("Viatura", cv, "_id=?", new String[]{_id});

        if (retorno == -1) {
            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        }


    }


    public void atualizarAvarias(String avaria,String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("Avaria",avaria);
            db.update("Avarias", cv, "_id=?", new String[]{_id});

        }

    public void atualizarCarater(Carater carater, String _id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Numeros",carater.getNumeros());
        cv.put("Letras",carater.getLetras());
        cv.put("Modelo",carater.getModelo());
        cv.put("Cor",carater.getCor());
        cv.put("Ano",carater.getAno());
        cv.put("Area",carater.getArea());
        cv.put("Natureza",carater.getNatureza());

        long retorno = db.update("Carater", cv, "_id=?", new String[]{_id});

        if (retorno == -1) {
            Toast.makeText(context, "Erro ao salvar os dados.", Toast.LENGTH_SHORT).show();
        }


    }




}//fim da classe
