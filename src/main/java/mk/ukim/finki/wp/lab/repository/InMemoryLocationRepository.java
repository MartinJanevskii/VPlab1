package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    static List<Location> locationList = new ArrayList<>();

    public InMemoryLocationRepository(){
        locationList.add(new Location("Akron", "Ohio","180000", "LeBron's hometown"));
        locationList.add(new Location("Los Angeles","Blagoja Toska 11","150000","Teteks homeground"));
        locationList.add(new Location("Skopje", "Partizanska","500000", "Finki, feit, tmf"));
        locationList.add(new Location("Kumanovo","Petachko vodiche 21","70000","The city of sport"));
        locationList.add(new Location("Ohrid", "ASNOM 7","310000", "Vacation home for families"));
    }

    public List<Location> findAll(){
        return locationList;
    }
    public Optional<Location> findById(Long id){
        return  locationList.stream().filter(x->x.getId().equals(id)).findFirst();
    }

}
