package fr.adaming.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;
import fr.adaming.service.IEtudiantService;

@Controller
@RequestMapping("/ecole")
public class EtudiantController {

	// Trasformation de l'association UML en JAVA
	@Autowired
	private IEtudiantService etuService;

	private Formateur formateur;

	// le setter pour l'injection de dependance
	public void setEtuService(IEtudiantService etuService) {
		this.etuService = etuService;
	}

	@PostConstruct
	public void init() {
		this.formateur = new Formateur(1, "a", "a");
	}

	// la methode appelée pour convertir les valeurs des params de la requete en
	// ObjetJAva (la date)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// l'objet WebDataBinder sert à faire le lien entre les params de la
		// requete et les objet java

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		df.setLenient(false);

		// la mthode registerCustomEditor(): à configuerer la conversion du
		// param recu au type de l'attribut

		// l'objet CustomDateEditor : sert à lier la date recu commpe param de
		// la requete à l'attribut de l'objet etudiant
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));

	}

	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		// recuperer l aliste des etudiants du formateur
		List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);

		return new ModelAndView("accueil", "etudiants", listeEtudiants);
	}

	// ===== Fonctionalité ajouter Etudiant
	@RequestMapping(value = "/afficheAdd", method = RequestMethod.GET)
	public String afficheAjouter(Model modele) {
		modele.addAttribute("eAdd", new Etudiant());
		return "ajout";
	}

	@RequestMapping(value = "/submitAdd", method = RequestMethod.POST)
	public String soumettreAjouter(ModelMap modele, @ModelAttribute("eAdd") Etudiant eIn) {
		// appel de la methode service
		Etudiant eOut = etuService.addEtudiant(eIn, this.formateur);

		if (eOut.getId() != 0) {
			// recuperer l aliste des etudiants du formateur
			List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);

			// mettre ajour la liste dans le model MVC
			modele.addAttribute("etudiants", listeEtudiants);

			return "accueil";

		} else {
			return "redirect:afficheAdd"; // redirection vers la methode
											// afficheAdd pour reafficher le
											// formulaire et lui associer
											// l'etudiant comme model
		}
	}

	// ============== Fonctionalité modifier etudiant
	@RequestMapping(value = "/afficheUpdate", method = RequestMethod.GET)
	public ModelAndView afficheModif() {
		return new ModelAndView("modif", "eUpdate", new Etudiant());
	}

	@RequestMapping(value = "/submitUpdate", method = RequestMethod.POST)
	public String soumettreModif(RedirectAttributes rda, Model modele, @ModelAttribute("eUpdate") Etudiant eIn) {
		// appel de la methode service
		boolean verif = etuService.updateEtudiant(eIn, this.formateur);
		if (verif) {
			return "redirect:liste";
		} else {
			rda.addFlashAttribute("msg", "L'etudiant n'est pas modifié!!!");
			return "redirect:afficheUpdate";
		}
	}

	// ================ Fonctionalité suuprimer etudiant
	@RequestMapping(value = "/afficheDelete", method = RequestMethod.GET)
	public String affihcheSupprimer() {
		return "supprission";
	}

	@RequestMapping(value = "/submitDelete", method = RequestMethod.GET)
	public String soummettreSupprimer(RedirectAttributes rda, @RequestParam("pId") int idIn) {

		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		boolean verif = etuService.deleteEtudiant(eIn, this.formateur);

		if (verif) {
			return "redirect:liste";
		} else {

			// l'objet RedirectAttributes: sert à transporter les attributs du
			// model mvc lors de la redirection
			rda.addFlashAttribute("msg", "L'etudiant n'est pas supprimé!!!");
			return "redirect:afficheDelete";
		}
	}
	
	
	//=============== Fonctionalité Rechercher
	@RequestMapping(value = "/afficheGet", method = RequestMethod.GET)
	public String affihcheRechercher() {
		return "recherche";
	}
	
	@RequestMapping(value = "/submitGet", method = RequestMethod.GET)
	public String soummettreRechercher(RedirectAttributes rda, @RequestParam("pId") int idIn) {

		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		Etudiant eOut = etuService.getEtudiantById(eIn);

		if (eOut!=null) {
			
			rda.addFlashAttribute("eGet",eOut);
			return "redirect:afficheGet";
		} else {

			// l'objet RedirectAttributes: sert à transporter les attributs du
			// model mvc lors de la redirection
			rda.addFlashAttribute("msg", "L'etudiant n'existe pas!!!");
			return "redirect:afficheGet";
		}
	}
	
	
	@RequestMapping(value = "/linkUpdate", method = RequestMethod.GET)
	public String getModifLien(Model modele, @RequestParam("pId") int idIn){
		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);

		Etudiant eOut = etuService.getEtudiantById(eIn);
		
		modele.addAttribute("eUpdate",eOut);
		
		
		return "modif";
	}

}
