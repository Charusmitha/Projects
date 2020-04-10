//
//  RegisterViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/6/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth


class RegisterViewController: UIViewController {
    
    @IBOutlet weak var txtName: UITextField!
    
    @IBOutlet weak var txtEmail: UITextField!
    
    @IBOutlet weak var txtPassword: UITextField!
    
    @IBOutlet weak var txtReenterPassword: UITextField!
    
    @IBOutlet weak var lblWelcome: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func register(_ sender: Any) {
        let email = txtEmail.text!
        let password = txtPassword.text!
        let fullName = txtName.text!
        let reenter = txtReenterPassword.text!
        
        if !email.isEmail || email.isEmpty {
            lblWelcome.text = "Please enter valid e mail"
            return
        }
        if password.count < 6 {
            lblWelcome.text = "Password should be bigger than 6"
            return
        }
        if password != reenter{
            lblWelcome.text = "Passwords dont match"
            return
        }
        Auth.auth().createUser(withEmail: email, password: password) { authResult, error in
          // [START_EXCLUDE]
            
            guard let user = authResult?.user, error == nil else {
                self.lblWelcome.text = error!.localizedDescription
              return
            }
            
            let currentUser = User();
            currentUser.uid = user.uid;
            currentUser.email = user.email!;
            currentUser.name = fullName;
            
            let changeRequest = Auth.auth().currentUser?.createProfileChangeRequest()
            changeRequest?.displayName = fullName
            changeRequest?.commitChanges { (error) in
                print(error)
            }
        
            print("\(user.email!) created")
            let alert = UIAlertController(title: "User Created", message: "", preferredStyle: .alert)

            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action) in
                self.navigationController?.popViewController(animated: true)
            }))

            self.present(alert, animated: true)
        }
    }
    
}
