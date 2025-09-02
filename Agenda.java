import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

/*********************************************************
METODOS DISPONIBILIZADOS:

public Agenda(int tam, String nmAgenda)
public Agenda()
public void Inicializar()
public int Cadastrar(String nm, String cel, String eml, LocalDate niver)
public int getNumeroCadastros()
public String getMensagemStatus(int st)
public int Alterar(int pos,String nm, String cel, String eml, LocalDate niver)
public int Deletar(String nm)
public int Deletar(int p)
public int Consultar(String nm)
public String getNome(int pos)
public String getCelular(int pos)
public String getEmail(int pos)
public LocalDate getDataAniversario(int pos)
public int getStatus(int pos)
public int lerArquivo()
public int gravarArquivo()

**********************************************************/
public class Agenda
{
    
    //ATRIBUTOS DA CLASSE
    private int maxCadastros=1000;
    static private Dados[] dd;   //=new Dados[1000];
    private int nCadastros;

    private String nomeAgenda;
    
    //INPUT:
    //tam - NÚMERO MAXIMO DE CADASTROS
    //nmAgenda - NOME DA AGENDA
    public Agenda(int tam, String nmAgenda)
    {
        nomeAgenda=nmAgenda;
        maxCadastros=tam;
        dd=new Dados[maxCadastros];
        nCadastros=0;
        
        for(int i=0;i<maxCadastros;i++)
        {
            dd[i]=new Dados();
        }
    };
    
    public Agenda()
    {
        nomeAgenda="Agenda";
        dd=new Dados[maxCadastros];
        nCadastros=0;
        for(int i=0;i<maxCadastros;i++)
        {
            dd[i]=new Dados();
        }
    };
    
    //INICIALIZAR DADOS DA AGENDA
    public void Inicializar()
    {
        nCadastros=0;
        for(int i=0;i<maxCadastros;i++)
        {
            dd[i].Inicializar();
        }  
    }
    
    //********************************
    //NUMERO DE CADASTROS NA AGENDA
    //********************************
    public int getNumeroCadastros()
    {
        return nCadastros;
    }
    
    //*****************************
    //CADASTRAR DADOS NA AGENDA
    //*****************************
    //INPUT:
    //String nm - NOME
    //String cel - CELULAR
    //String eml - EMAIL
    //LocalDate niver - DATA NASCIMENTO
    //
    //OUTPUT:
    //STATUS:
    //0 - Nao Cadastrado ;  1 - Cadastrado ; 2 - Alterado ; 3 - Deletado
    public int Cadastrar(String nm, String cel, String eml, LocalDate niver)
    {
        int status;
        
        status=dd[nCadastros].Cadastrar(nm, cel, eml, niver);
        if (status==1)
        {
            nCadastros++;
        }
        
        return status;
    };
    
    public String getMensagemStatus(int st)
    {
          switch(st)
          {
            case 0:
                  return "NAO CADASTRADO";
                  //break;
            case 1:
                  return "CADASTRO OK";
                  //break;
            case 2:
                  return "CADASTRO ALTERADO";
                  //break;      
            case 3:
                  return "CADASTRO DELETADO";
                  //break;
            default:
                  return "STATUS NAO VALIDO";
          }
    };
    
    //*****************************
    //ALTERAR DADOS NA AGENDA
    //*****************************
    //INPUT:
    //String nm - NOME
    //String cel - CELULAR
    //String eml - EMAIL
    //LocalDate niver - DATA NASCIMENTO
    //
    //OUTPUT:
    //STATUS:
    //0 - Nao Cadastrado ;  1 - Cadastrado ; 2 - Alterado ; 3 - Deletado
    public int Alterar(int pos,String nm, String cel, String eml, LocalDate niver)
    {
        int status=dd[pos].getStatus();
        if((status==1)||(status==2))
        {
         status=dd[pos].Alterar(nm, cel, eml, niver);
        }
        
        return status;
    };
    
    //***********************************
    //DELETAR POR NOME - DADOS NA AGENDA
    //***********************************
    //INPUT:
    //String nm - NOME
    //
    //OUTPUT:
    //STATUS:
    //0 - Nao Cadastrado ;  1 - Cadastrado ; 2 - Alterado ; 3 - Deletado
    public int Deletar(String nm)
    {
        int pos=Consultar(nm);
        int status=0;
        boolean flag;
        if (pos>=0)
        {
            status=dd[pos].getStatus();
        
            if((status==1)||(status==2))
             {
               if(dd[pos].Deletar(nm)==true)
                  return 3;
              }
        }
        return status;
    };

    //***********************************
    //DELETAR POR POSICAO - INDEXADOR
    //***********************************
    //INPUT:
    //int pos - numero do registro
    //
    //OUTPUT:
    //STATUS:
    //0 - Nao Cadastrado ;  1 - Cadastrado ; 2 - Alterado ; 3 - Deletado
    public int Deletar(int p)
    {
        if (dd[p].Deletar()==true)
               return 3;
  
        return -1;
    };   
        
    //*****************************
    //CONSULTAR POR NOME
    //*****************************
    //INPUT:
    //String nm - NOME
    //
    //OUTPUT:
    //Numero do registro de posicao na AGENDA
    //    registro >=0  - nome encontrado e registro válido
    //                    retorna numero do registro
    //    registro <0   - nome nao encontrado ou registro nao valido
    public int Consultar(String nm)
    {
        int registro=-1;
        
        for (int i = 0 ; i<nCadastros;i++)
        {
            if(dd[i].Consultar(nm)==true)
            {
                System.out.println("REGISTRO RETORNO ["+String.format("%d",i)+"]: ");
                return i;
            }
        }
        return registro;
    };
    
   //*****************************
    //GET Nome
    //*****************************
    //INPUT:
    //int pos - posicao
    //
    //OUTPUT:
    //Nome do cadastro
    public String getNome(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
          return dd[pos].getNome();  
        }
        
        return null;
    };

   //*****************************
    //GET Celular
    //*****************************
    //INPUT:
    //int pos - posicao
    //
    //OUTPUT:
    //Celular do cadastro
    public String getCelular(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
          return dd[pos].getCelular();  
        }
        
        return null;
    };
  
   //*****************************
    //GET Email
    //*****************************
    //INPUT:
    //int pos - posicao
    //
    //OUTPUT:
    //Email do cadastro
    public String getEmail(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
          return dd[pos].getEmail();  
        }
        
        return null;
    };
    
   //*****************************
    //GET DATA ANIVERSARIO
    //*****************************
    //INPUT:
    //int pos - posicao
    //
    //OUTPUT:
    //DATA ANIVERSARIO do cadastro
    public LocalDate getDataAniversario(int pos)
    {
        int st=dd[pos].getStatus();
        if ((st==1)||(st==2))
        {
          return dd[pos].getDataAniversario();  
        }
        
        return null;
    };

   //*****************************
    //GET STATUS
    //*****************************
    //INPUT:
    //int pos - posicao
    //
    //OUTPUT:
    //STATUS   
    public int getStatus(int pos)
    {
        if ((pos>0)||(pos<nCadastros))
        {
          return dd[pos].getStatus();
        }
        
        return 0;
    };
    
    public int lerArquivo()
    {
       String nm;
       String cel;
       String eml;
       LocalDate niver;
       int dia,mes,ano;
       int status;
       
       try
       {
          BufferedReader reader = new BufferedReader(new FileReader(nomeAgenda+".txt"));
          String linha=" ";
          while (linha != null) 
          {
            //LER NOME
            linha = reader.readLine();
            if (linha!=null)
            {
                //System.out.println("NOME: "+linha);
                nm=linha;
            
                //LER CELULAR
                linha=reader.readLine();
                //System.out.println("CELULAR: "+linha);
                cel=linha;
            
                //LER EMAIL
                linha=reader.readLine();
                //System.out.println("EMAIL: "+linha);
                eml=linha;
            
                //LER DIA ANIVERSARIO
                linha=reader.readLine();
                //System.out.println("DIA: "+linha);
                dia=Integer.parseInt(linha);
                //LER MES ANIVERSARIO
                linha=reader.readLine();
                //System.out.println("MES: "+linha);
                mes=Integer.parseInt(linha);
                //LER ANO ANIVERSARIO
                linha=reader.readLine();
                //System.out.println("ANO: "+linha);
                ano=Integer.parseInt(linha);
            
                niver=LocalDate.of(ano, mes, dia);
                //System.out.println("NIVER: "+niver);
            
                status=Cadastrar(nm, cel, eml, niver);
             }
            }
            
          reader.close();    
        } 
        catch (FileNotFoundException e) 
        {
            //System.err.println("Arquivo não encontrado: " + e.getMessage());
            return -1;
        } 
        catch (IOException e) 
        {
            //System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return -2;
        }  

        return nCadastros;
    };
 
    public int gravarArquivo()
    {
       String nm;
       String cel;
       String eml;
       LocalDate niver;
       int dia,mes,ano;
       int status;
       
       try
       {
          BufferedWriter bufferWrite = new BufferedWriter(new FileWriter(nomeAgenda+".txt"));
          for (int i = 0;i<nCadastros;i++)
          {
             bufferWrite.append(dd[i].getNome()+"\n");
             bufferWrite.append(dd[i].getCelular()+"\n");
             bufferWrite.append(dd[i].getEmail()+"\n");
             
             niver=dd[i].getDataAniversario();
             dia = niver.getDayOfMonth();
             mes = niver.getMonthValue(); 
             ano = niver.getYear();
             bufferWrite.append(dia+"\n");
             bufferWrite.append(mes+"\n");
             bufferWrite.append(ano+"\n");
          }
          bufferWrite.close();
        } 
        catch (FileNotFoundException e) 
        {
            //System.err.println("Arquivo não encontrado: " + e.getMessage());
            return -1;
        } 
        catch (IOException e) 
        {
            //System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return -2;
        }  
        
        return nCadastros;
    };
    
    
};