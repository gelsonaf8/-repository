package com.example.CadastroOcorrencias.defaultConfig;

import com.example.CadastroOcorrencias.model.Equipe;

import java.util.ArrayList;
import java.util.List;

public class defaultConfig {


    public static String[] ListaNatureza(){


        return new String[]{
                "A05 - Agressão",
                "A10 - Ameaça",
                "A12 - Violação de domicilio",
                "A13 - Maus tratos a pessoa",
                "A20 - Pedido de socorro",
                "A21 - Encontro de cadaver",
                "A22 - Suícidio",
                "A23 - Tentativa de suícidio",
                "A98 - Violência domestica",

                "B01 - Furto",
                "B02 - Furto tentativa",
                "B03 - Roubo tentado",
                "B04 - Roubo",

                "C01 - Perturbação do sossego",
                "C03 - Embriaguez",
                "C04 - Desinteligência",
                "C05 - Atitude suspeita",
                "C99 - Pancadão",

                "E07 - Apoio",

                "F01 - Ocorrência com entorpecente",
                "F02 - Uso de entorpecente",

                "G01 - Ocorrência com preso",
                "J09 - Arma de fogo",
                "L01 - Veículo",
                "L08 - Acidente de trânsito com vítima",
                "L09 - Acidente de trânsito sem vítima",
                "L12 - Acidente de trânsito moto",

                "M01 - Ocorrência com pessoa",

                "N01 - Incêndio",

                "Z12 - Fucionamento s/ autor. - Covid-19"};
    }

    public static String[] listaResultado(){


        return new String[]{"Nada constatado","Partes orientadas","Elaborado NOC","Elaborado BOPM","Elaborado BOPC","Resolvida pela GCM"};
    }


    public String[] ListaAcessorios() {


        return new String[]{"RIV","CRLV","Cartão de Abastecimento","Triângulo","Rádio Operacional","Bola de Câmbio","Acendedor/Tomada 12v","Espelho Retrovisor Interno",
        "Tapetes(Qtde)","Caixa de 1º Socorros","Chave de Ignição","Res. Limpador de Para-brisa",
        "Extintor","Encosto de cabeça","Alça de Segurança(TETO)","Chave de Roda","Macaco","Ferramentas",
        "Pneu/Estepe","Antena","Giroflex","Calota(Qtde)","Rodas de Liga Leve","Óleo de Motor"};
    }

    public String[] ListaFuncionamento() {


        return new String[]{"Radio Operacional","Sirene","Giroflex","Farol dianteiro esquerdo",
        "Farol dianteiro direito","Lanterna traseira esquerda","Lanterna traseira direita",
        "Luz de ré esquerda","Luz de ré direita","Iluminação da placa","Limpador de Para-brisa",
        "Reservador de Agua do Radiador"};
    }

    public List<Equipe> ListaEquipe(){

        List<Equipe> listaEquipe = new ArrayList<>();

        Equipe equipe = new Equipe();
        equipe.setId(1);
        equipe.setGraduacao("");
        equipe.setRE("");
        equipe.setNome("");
        equipe.setTelefone("");
        equipe.setFuncao("Encarregado");
        listaEquipe.add(equipe);

        Equipe equipe1 = new Equipe();
        equipe1.setId(2);
        equipe1.setGraduacao("");
        equipe1.setRE("");
        equipe1.setNome("");
        equipe1.setTelefone("");
        equipe1.setFuncao("Motorista");
        listaEquipe.add(equipe1);


        Equipe equipe3 = new Equipe();
        equipe3.setId(3);
        equipe3.setGraduacao("");
        equipe3.setRE("");
        equipe3.setNome("");
        equipe3.setTelefone("");
        equipe3.setFuncao("Auxiliar 1");
        listaEquipe.add(equipe3);


        Equipe equipe4 = new Equipe();
        equipe4.setId(4);
        equipe4.setGraduacao("");
        equipe4.setRE("");
        equipe4.setNome("");
        equipe4.setTelefone("");
        equipe4.setFuncao("Auxiliar 2");
        listaEquipe.add(equipe4);


      return listaEquipe;
    }


}
