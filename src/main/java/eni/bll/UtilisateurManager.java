package eni.bll;

import java.rmi.ServerException;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import eni.bll.exception.BLLException;
import eni.bo.Utilisateur;
import eni.dal.DaoFactory;
import eni.dal.UtilisateurDao;
import eni.dal.jdbc.exception.JDBCException;
import eni.helper.PasswordEncoder;
import jakarta.servlet.http.HttpSession;

public class UtilisateurManager {

private static UtilisateurManager instance;
	
	private UtilisateurManager() {}
	
	public static UtilisateurManager getInstance() {
		if(instance==null) instance = new UtilisateurManager();
		return instance;
	}
	
	private UtilisateurDao utilisateurDao = DaoFactory.getUtilisateurDao();
	
	public void inscription(Utilisateur utilisateur) throws JDBCException, BLLException, SQLServerException {
		// validation !!!!!!!!
		checkFields(utilisateur);
		utilisateur.setMotDePasse( PasswordEncoder.hashPassword(utilisateur.getMotDePasse()));
		
		utilisateur.setCredit(0);
		utilisateur.setAdministrateur(false);
		utilisateurDao.save(utilisateur);		
	}
	
	private void checkFields(Utilisateur utilisateur) throws BLLException {
		if( utilisateur == null ) throw new BLLException("User est null");
		
		if( utilisateur.getPseudo().isBlank() ) throw new BLLException("Le champs pseudo est obligatoire !");
		if( utilisateur.getNom().isBlank() ) throw new BLLException("Le champs nom est obligatoire !");
		if( utilisateur.getPrenom().isBlank() ) throw new BLLException("Le champs prénom est obligatoire !");
		if( utilisateur.getEmail().isBlank() ) throw new BLLException("Le champs email est obligatoire !");
		if( utilisateur.getTelephone().isBlank() ) throw new BLLException("Le champs téléphone est obligatoire !");
		if( utilisateur.getRue().isBlank() ) throw new BLLException("Le champs rue est obligatoire !");
		if( utilisateur.getCodePostal().isBlank() ) throw new BLLException("Le champs code postal est obligatoire !");
		if( utilisateur.getVille().isBlank() ) throw new BLLException("Le champs ville est obligatoire !");
		
		if ( !StringUtils.isAlphanumeric(utilisateur.getPseudo())) throw new BLLException("Pas de charactères spéciaux merci !");
		
		if( utilisateur.getMotDePasse().isBlank() ) throw new BLLException("Le champs mot de passe est obligatoire!");
		if( utilisateur.getMotDePasse().length() < 8 ||  utilisateur.getMotDePasse().length() > 35 )throw new BLLException("La taille du mot de passe doit etre entre 8 et 35");
	}

	public Utilisateur login(String pseudoOrEmail,String password) {	
		
		Utilisateur utilisateur;
		
		if (pseudoOrEmail.contains("@")) {
			
			utilisateur = utilisateurDao.findByEmail(pseudoOrEmail);		
			
		}else {
			utilisateur = utilisateurDao.findByPseudo(pseudoOrEmail);
		}

		if(utilisateur!=null && PasswordEncoder.verifyPassword(password, utilisateur.getMotDePasse()) ) {
			return utilisateur;
		}		
		return null;
	}
	
	public Utilisateur recupererUtilisateurById(int noUtilisateur) {
		Utilisateur utilisateur = utilisateurDao.findById(noUtilisateur);
		return utilisateur;
	}
	
	public void supprimerUnUtilisateur(String pseudo, String motDePasse) throws BLLException {
			
		if( motDePasse.isBlank() ) throw new BLLException("Le champs mot de passe est obligatoire!");
		
		Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo);
		
		
		if (!PasswordEncoder.verifyPassword(motDePasse, utilisateur.getMotDePasse())) {
			throw new BLLException("Le mot de passe n'est pas bon !");
		}
			utilisateurDao.remove(pseudo);
		
	}
	
	public void modificationUtilisateur(Utilisateur utilisateur, String nouveauMotDePasse) throws BLLException {
		
		checkFields(utilisateur);
		
		Utilisateur ancienUtilisateur = utilisateurDao.findById(utilisateur.getNoUtilisateur());
			
		String password = utilisateur.getMotDePasse();
		
//		nouveauMotDePasse = PasswordEncoder.hashPassword(nouveauMotDePasse);
		
		if (!PasswordEncoder.verifyPassword(password, ancienUtilisateur.getMotDePasse())) {
			throw new BLLException("Le mot de passe n'est pas bon !");
		}
		
		utilisateurDao.modify(utilisateur, PasswordEncoder.hashPassword(nouveauMotDePasse));	
		
		
//		if(ancienUtilisateur!=null && PasswordEncoder.verifyPassword(password, ancienUtilisateur.getMotDePasse()) ) {
//			utilisateurDao.modify(utilisateur, nouveauMotDePasse);	
//		}					  	
	}
}
