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
		utilisateur.setMotDePasse( PasswordEncoder.hashPassword(
				utilisateur.getMotDePasse()// password no hashé
					)
				);
		
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
		
		
		
		// verifier la syntaxe de l'email
		if( utilisateur.getMotDePasse().isBlank() ) throw new BLLException("Le champs mot de passe est obligatoire!");
		if( utilisateur.getMotDePasse().length() < 8 ||  utilisateur.getMotDePasse().length() > 35 )throw new BLLException("La taille du mot de passe doit etre entre 8 et 35");
		//if(!user.getPassword().equals(user.getConfirmpassword))
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
	
	public void supprimerUnUtilisateur(String pseudo, String motDePasse) {
		Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo);
		
		
		
		if (PasswordEncoder.verifyPassword(motDePasse, utilisateur.getMotDePasse())) {
			utilisateurDao.remove(pseudo);
		}
	}
}
