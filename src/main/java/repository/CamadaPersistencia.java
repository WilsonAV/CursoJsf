package repository;

import model.Empresa;
import model.RamoAtividade;
import model.TipoEmpresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CamadaPersistencia {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Declarando os repositórios
        RamoAtividades ramoAtividades = new RamoAtividades(em);
        Empresas empresas = new Empresas(em);

        //Buscando as informações do banco
        List<RamoAtividade> listadeRamoAtividades = ramoAtividades.pesquisar("");
        List<Empresa> listaDeEmpresas = empresas.pesquisar("");
        System.out.println(listaDeEmpresas);

        //Criando uma Empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("João da Silva");
        empresa.setCnpj("41.952.519/0001-57");
        empresa.setRazaoSocial("João da Silva 41952519000157");
        empresa.setTipo(TipoEmpresa.MEI);
        empresa.setDataFundacao(new Date());
        empresa.setRamoAtividade(listadeRamoAtividades.get(0));

        empresas.guardar(empresa);

        em.getTransaction().commit();

        //Verificando se a inserção funcionou
        List<Empresa> listadeEmpresas2 = empresas.pesquisar("");
        System.out.println(listadeEmpresas2);

        em.close();
        emf.close();
    }
}
