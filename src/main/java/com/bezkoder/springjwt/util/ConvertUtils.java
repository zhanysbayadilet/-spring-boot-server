package com.bezkoder.springjwt.util;

import com.bezkoder.springjwt.dto.CategoryDTO;
import com.bezkoder.springjwt.dto.TournamentDTO;
import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.Tournament;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static TournamentDTO convertToTournamentDTO(Tournament tournament) {
        return new TournamentDTO().toDto(tournament);
    }


    public static Tournament convertToTournament(TournamentDTO tournamentDTO) {
        Tournament tournament = new Tournament();
        tournament.setId(tournamentDTO.getId());
        tournament.setName(tournamentDTO.getName());
        tournament.setDescription(tournamentDTO.getDescription());
        tournament.setTournament_img(tournamentDTO.getTournament_img());
        tournament.setStart_date(tournamentDTO.getStart_date());
        tournament.setEnd_date(tournamentDTO.getEnd_date());
        tournament.setPrize_fund(tournamentDTO.getPrize_fund());
        tournament.setCategory(convertToCategory(tournamentDTO.getCategory()));
        tournament.setUsers(tournamentDTO.getUsers());
        return tournament;
    }

    public static CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO().toDto(category);
    }

    public static Category convertToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setCategory_name(categoryDTO.getCategory_name());
        category.setCategory_description(categoryDTO.getCategory_description());
        category.setCategory_img(categoryDTO.getCategory_img());
        return category;
    }

    public static List<CategoryDTO> convertCategoryListToDtoList(List<Category> categories) {
        ArrayList<CategoryDTO> categoryDTOArrayList = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOArrayList.add(convertToCategoryDTO(category));
        }
        return categoryDTOArrayList;
    }

    public static List<TournamentDTO> convertTournamentListToDtoList(List<Tournament> tournaments) {
        ArrayList<TournamentDTO> tournamentDTOArrayList = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            tournamentDTOArrayList.add(convertToTournamentDTO(tournament));
        }
        return tournamentDTOArrayList;
    }
}
