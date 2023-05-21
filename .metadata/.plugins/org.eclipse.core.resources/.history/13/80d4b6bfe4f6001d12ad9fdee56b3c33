import java.io.*;

public class SaveLoadManager {
    private static final String FILE_PATH = "data.csv";

    public static void saveData(UserData userdata) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(userdata.getBoxCount()));
            writer.newLine();
            writer.write(String.valueOf(userdata.getTrainerLV()));
            writer.newLine();

            int[] candies = userdata.getEXPCandyCount();
            for (int candy : candies) {
                writer.write(String.valueOf(candy));
                writer.newLine();
            }

            Pokemon[] gotPokemon = userdata.getPokemon();
            for (Pokemon pokemon : gotPokemon) {
                writer.write(pokemon.index);
                writer.newLine();
                writer.write(pokemon.add);
                writer.newLine();
              }

            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    public static void loadData(UserData userdata) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            int boxCount = Integer.parseInt(reader.readLine());
            int trainerLV = Integer.parseInt(reader.readLine());

            int[] candies = new int[5];
            for (int i = 0; i < 5; i++) {
                candies[i] = Integer.parseInt(reader.readLine());
            }

            userdata.setBoxCount(boxCount);
            userdata.setTrainerLv(trainerLV);
            userdata.setEXPCandyCount(candies);

            Pokemon[] gotPokemon = new Pokemon[5];
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 5) {
                String[] pokemonData = line.split(",");
                // Parse the pokemon data and create a new Pokemon object
                // Assuming the CSV format is: name,HP,ATK,DEF,SPD,type,Lv,skillname
                int dex = Integer.parseInt(pokemonData[0]);
                int add = Integer.parseInt(pokemonData[1]);

                Pokemon pokemon = new Pokemon(dex,add);
                

                gotPokemon[dex++] = pokemon;
            }

            userdata.setGotPokemon(gotPokemon);

            System.out.println("Data loaded successfully!");
        } catch (IOException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
  }
}