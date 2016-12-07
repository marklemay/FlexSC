package example.runner;

import java.util.Arrays;

import org.apache.commons.cli.ParseException;

import example.Millionaire;
import example.Millionaire.Generator;
import flexsc.Flag;
import flexsc.Mode;
import util.ConfigParser;
import util.EvaRunnable;
import util.GenRunnable;

public class runMill {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

		// example.Millionaire.Generator.main(args);

		// GenRunnable<T>

		Runnable taskGen = () -> {

			ConfigParser config = new ConfigParser("Config.conf");

			Generator run = (new Millionaire.Generator());

//			Host: localhost
//			Port: 54321
//			Mode:OPT
			
			String[] argss = { "example.Millionaire", "3" };
//			run.setParameter("localhost", 54321);
			run.setParameter(config, Arrays.copyOfRange(argss, 1, argss.length));
			run.run();

		};

		Runnable taskEva = () -> {
			try {
				EvaRunnable.main(new String[] { "example.Millionaire", "4" });
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		(new Thread(taskGen)).start();
		(new Thread(taskEva)).start();

	}

}
