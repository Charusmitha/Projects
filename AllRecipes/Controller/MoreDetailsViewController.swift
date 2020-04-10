//
//  MoreDetailsViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/8/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
import FirebaseAuth
import RealmSwift

class MoreDetailsViewController: UIViewController {
    
    var recipeId: String?
    var details = RecipeDetails()
    
    @IBOutlet weak var lblRecipeName: UILabel!
    
    @IBOutlet weak var imageRecipe: UIImageView!
    
    @IBOutlet weak var lblRecipeTime: UILabel!
    
    @IBOutlet weak var lblRecipeServing: UILabel!
    
    @IBOutlet weak var textRecipeInstructions: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        GetRecipeDetails(recipe: recipeId!)
    }
    
    func GetRecipeDetails(recipe: String) {
        let url = "https://api.spoonacular.com/recipes/\(recipe)/information?includeNutrition=false&apiKey=7e400b887da34b0cbfc19afa5acce478";
               if url == "" {
                   return
               }
               Alamofire.request(url, method: .get, parameters: nil)
                   .responseJSON {
                       response in
                    if response.result.isSuccess{
                        let recipeJSON: JSON = JSON(response.result.value!)
                        self.details.recipeName = recipeJSON["title"].rawString()!
                        self.details.recipeIcon = recipeJSON["image"].rawString()!
                        self.details.recipeTime = recipeJSON["readyInMinutes"].rawString()!
                        self.details.recipeServing = recipeJSON["servings"].rawString()!
                        self.details.recipeInstructions = recipeJSON["instructions"].rawString()!
                        
                        self.lblRecipeName.text = self.details.recipeName
                        self.lblRecipeTime.text = self.details.recipeTime
                        self.lblRecipeServing.text = self.details.recipeServing
                        self.textRecipeInstructions.text = self.details.recipeInstructions
                        let imgURL = self.details.recipeIcon
                        self.imageRecipe.setImageFromURl(stringImageUrl: imgURL)
                    }
                     
               }
    }
    
    
    @IBAction func Favourites(_ sender: Any) {
        let alert = UIAlertController(title: "Added to favourites", message: "", preferredStyle: .alert)

        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action) in
            self.navigationController?.popViewController(animated: true)
        }))

        self.present(alert, animated: true)
        
        let favouriteSelected = Favourite();
        favouriteSelected.uid = Auth.auth().currentUser!.uid;
        favouriteSelected.recipeIcon = details.recipeIcon;
        favouriteSelected.recipeName = details.recipeName;
        AddFavouritesToDB(favourite: favouriteSelected)
    }
    
    func AddFavouritesToDB(favourite: Favourite){
        do{
            let realm = try Realm()
            try realm.write {
                realm.add(favourite, update: Realm.UpdatePolicy.all)
            }

        }catch{
            print("Error in Adding Favourite to DB \(error)")
        }
    }
    
}

extension UIImageView{

func setImageFromURl(stringImageUrl url: String){

      if let url = NSURL(string: url) {
         if let data = NSData(contentsOf: url as URL) {
            self.image = UIImage(data: data as Data)
         }
      }
   }
}


