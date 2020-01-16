package fr.adaming.testDao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IEtudiantDao;
import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class EtudiantDaoImplTest {

	// Injecter dao
	@Autowired
	IEtudiantDao eDao;

	Formateur fIn;

	@Before
	public void setUp() {
		this.fIn = new Formateur();
		fIn.setId(1);
	}

	@Test
	@Transactional(readOnly = true)
	public void testGetAllEtudiantSize() {
		int resulAttendu = 1;

		List<Etudiant> listE = eDao.getAllEtudiants(fIn);
		int resulObtenu = listE.size();

		assertEquals(resulAttendu, resulObtenu);

	}

	@Test
	@Transactional(readOnly = true)
	public void testGetAllEtudiantsNom() {
		String resulAttendu = "toto";

		List<Etudiant> listE = eDao.getAllEtudiants(fIn);
		String resulObtenu = listE.get(0).getNom();

		assertEquals(resulAttendu, resulObtenu);

	}

	@Test
	@Transactional(readOnly = true)
	public void testGetEtudiantById() {

		Etudiant e1 = new Etudiant();

		e1.setId(5);

		Etudiant resultatObtenu = eDao.getEtudiantById(e1);

		assertNotNull(resultatObtenu);

	}
	
	@Test
	@Transactional(readOnly = true)
	public void testGetEtudiantByIdNom(){
		
		Etudiant e1 = new Etudiant();
		String resultatAttendu = "toto";
		e1.setId(5);

		String resultatObtenu = eDao.getEtudiantById(e1).getNom();
		
		assertEquals(resultatAttendu, resultatObtenu);
		
	}
	
	@Test
	@Transactional
	public void testAddEtudiant() {
		List<Etudiant> listeAvant = eDao.getAllEtudiants(fIn);
		int avant = listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(fIn);
		eDao.addEtudiant(etu);
		
		List<Etudiant> listeApres = eDao.getAllEtudiants(fIn);
		int apres = listeApres.size();
		
		assertEquals(avant+1, apres);
	}
	
	@Test
	@Transactional
	public void testAddEtudiantNom(){
		Etudiant expected =new Etudiant("Dubresson","Antoine",null);
		expected = eDao.addEtudiant(expected);
		
		Etudiant result = eDao.getEtudiantById(expected);
		
		assertEquals(expected.getNom(),result.getNom());
		
	}
	
	@Test
	@Transactional
	public void testDeleteEtudiant(){
		List<Etudiant> listeAvant=eDao.getAllEtudiants(fIn);
		int avant=listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(fIn);
		etu.setId(5);
		
		etu=eDao.getEtudiantById(etu);
		
		eDao.deleteEtudiant(etu);
		
		List<Etudiant> listeApres=eDao.getAllEtudiants(fIn);
		int apres=listeApres.size();
		
		assertEquals(avant-1, apres);
		
	}
	@Test
	@Transactional
	public void testUpdateEtudiant(){
		Etudiant eIn = new Etudiant(5, "toto", "titi", null);
		eDao.updateEtudiant(eIn);
		Etudiant eOut = eDao.getEtudiantById(eIn);
		assertEquals(eIn.getNom(), eOut.getNom());
		
		
		
		
		
		
		
	}
	
	

}
