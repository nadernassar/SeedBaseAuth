package edu.pace.csis.phd.sbauth.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import edu.pace.csis.phd.sbauth.model.Seed;
import edu.pace.csis.phd.sbauth.model.Token;
import edu.pace.csis.phd.sbauth.server.query.Queries;

public class SeedDAO {
	public SeedDAO() {
	}

	public void insertSeeds(Seed seed) {

		try {
			Queries.InsertSeeds(seed.getURLSeed(), seed.getUNMSeed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Seed getSeedRecord(Long urlSeed, Long unmSeed) {
		Seed seed = new Seed();
		return seed;
	}

	public static int FindUserBySeeds(Seed seed) {
		int userId = 0;
		try {

			userId = Queries.FindUserBySeeds(seed.getURLSeed(),
					seed.getUNMSeed());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	public static Seed FindSeedByUserId(int userId) {
		Seed seed = new Seed();
		try {

			seed = Queries.FindSeedByUserId(userId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seed;
	}

	public static void expireSeeds(Seed seed) {
		try {
			Queries.ExpireSeeds(seed.getURLSeed(), seed.getUNMSeed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}