package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Repository
public class EtudiantDaoImpl implements IEtudiantDao {

	private SessionFactory sf;

	@Autowired // cette annotation peut �tre appliqu�e � l'attribut ou bien au
				// setter
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Etudiant> getAllEtudiants(Formateur fIn) {
		// r�cup�rer la session
		Session s = sf.getCurrentSession();

		// �crire la requ�te hql
		String req = "FROM Etudiant e WHERE e.formateur.id=:pIdF";

		// cr�er un Query
		Query query = s.createQuery(req);

		// passage des valeurs aux param�tres
		query.setParameter("pIdF", fIn.getId());

		// envoi de la requ�te et r�cup�ration du r�sultat
		List<Etudiant> listeOut = query.list();

		return listeOut;
	}

	@Override
	public Etudiant getEtudiantById(Etudiant eIn) {
		// r�cup�rer la session en cours
		Session s = sf.getCurrentSession();

		// La m�thode de la session pour r�cup�re run etudiant by id
		Etudiant eOut=(Etudiant) s.get(Etudiant.class, eIn.getId());
		

		return eOut;
	}

	@Override
	public Etudiant addEtudiant(Etudiant eIn) {
		//r�cup�rer la session 
		Session s=sf.getCurrentSession();
		
		//ajouter un �tudiant
		s.save(eIn);
		
	
		return eIn;
	}

	@Override
	public void deleteEtudiant(Etudiant eIn) {
		// recupere la session
		Session s = sf.getCurrentSession();
		
		//Supprime l'etudiant
		s.delete(eIn);
		
	}

	@Override
	public void updateEtudiant(Etudiant eIn) {
		// recuperer la session
		Session s= sf.getCurrentSession();
		
		// modifier l'�tudiant
		s.saveOrUpdate(eIn);
		
	}

}
