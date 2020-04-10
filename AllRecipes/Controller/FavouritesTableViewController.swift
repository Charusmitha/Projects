//
//  FavouritesTableViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/8/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import RealmSwift
import FirebaseAuth

class FavouritesTableViewController: UITableViewController {
    
    var arr = [Favourite]()
    var recipeName: String?
    var recipeImage: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        LoadValuesFromDB()
        print(Realm.Configuration.defaultConfiguration.fileURL)
    }
    
    func LoadValuesFromDB(){
        do{
            let realm = try Realm()
            let favourites = realm.objects(Favourite.self)
            
            
            for favourite in favourites{
                if(favourite.uid == Auth.auth().currentUser!.uid)
                {
                    arr.append(favourite)
                }
            }
            tableView.reloadData()
        }catch{
            print("Error in Loading \(error)")
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arr.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = Bundle.main.loadNibNamed("FavouritesTableViewCell", owner: self, options: nil)?.first as! FavouritesTableViewCell
        let imgURL = arr[indexPath.row].recipeIcon
        
        cell.lblRecipeName.text = "\(arr[indexPath.row].recipeName)"
        cell.imageRecipe.setImageFromURl(stringImageUrl: imgURL)

        return cell
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
           if editingStyle == .delete {
            let favourite = arr[indexPath.row]
            DeleteFavouriteFromDB(favourite: favourite)
            arr.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
    
    func DeleteFavouriteFromDB(favourite: Favourite){
        let realm = try! Realm()
         try! realm.write {
            realm.delete(favourite)
        }
    }
    
}
