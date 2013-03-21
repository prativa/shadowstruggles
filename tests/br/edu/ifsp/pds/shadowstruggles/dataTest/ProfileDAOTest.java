package br.edu.ifsp.pds.shadowstruggles.dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;

import com.badlogic.gdx.utils.Array;

public class ProfileDAOTest {

	@Test
	public void test() {
		Array<Profile> profiles= ProfileDAO.getProfiles(new DataManager());
		assertEquals("Result:", 1, profiles.get(0).getId());
		
	}

}
