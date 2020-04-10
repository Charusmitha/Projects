//
//  HomeTableViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/7/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class HomeTableViewController: UITableViewController, UISearchBarDelegate {
    
    @IBOutlet weak var searchBar: UISearchBar!
    
    var arr = [Recipe]()

    override func viewDidLoad() {
        super.viewDidLoad()
        searchBar.delegate = self
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if(searchText == "") {
            arr.removeAll()
            self.tableView.reloadData()
        }
        else {
           GetSearchResults(searchFor: searchText)
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arr.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cell = Bundle.main.loadNibNamed("HomeTableViewCell", owner: self, options: nil)?.first as! HomeTableViewCell
        let imgURL = "https://spoonacular.com/recipeImages/\(arr[indexPath.row].recipeIcon)"
        
        cell.lblRecipeName.text = "\(arr[indexPath.row].recipeName)"
        cell.imageRecipe.setImageFromURl(stringImageUrl: imgURL)

        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        //print(arr[indexPath.row].recipeName)
        performSegue(withIdentifier: "moreSegue", sender: indexPath)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "moreSegue" {
            let detailsVC = segue.destination as! MoreDetailsViewController
            let indexPath = sender as! IndexPath
            detailsVC.recipeId = arr[indexPath.row].recipeId
        }
    }
    
    func GetSearchResults(searchFor: String){

        let url = "https://api.spoonacular.com/recipes/search?query=\(searchFor)&offset=0&number=15&apiKey=7e400b887da34b0cbfc19afa5acce478";
        if url == "" {
            return
        }
        Alamofire.request(url, method: .get, parameters: nil)
            .responseJSON {
                response in
                self.arr.removeAll()
                if response.result.isSuccess{
                    let recipeJSON: JSON = JSON(response.result.value!)
                    if let recipes = recipeJSON["results"].array {
                        for rp in recipes {
                            let recipe = Recipe()
                            recipe.recipeId = rp["id"].rawString()!
                            recipe.recipeName =  rp["title"].rawString()!
                            recipe.recipeIcon = rp["image"].rawString()!
                            self.arr.append(recipe)
                        }
                    }
                    self.tableView.reloadData()
                }
                else{
                    print(response.result.error ?? "Error in JSON file")
                }
        }
        
    }
    
}


