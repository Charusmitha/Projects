//
//  StocksTableViewController.swift
//  StockTicker
//
//  Created by Charusmitha Deshpande on 10/1/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit

class StocksTableViewController: UITableViewController {

    var stocks = ["GOOGL", "AAPL", "AMZN", "MSFT", "TSLA"]
    // MARK: - Table view data source


    @IBAction func AddStock(_ sender: Any) {
        let alert = UIAlertController(title: "Add a new stock", message: "", preferredStyle: .alert)

        alert.addTextField { (textField) in
            textField.text = ""
        }

        alert.addAction(UIAlertAction(title: "Add", style: .default, handler: { [weak alert] (_) in
            let textField = alert?.textFields![0]
            self.stocks.append("\(String(textField!.text!))")
            self.tableView.reloadData()
        }))
        
        alert.addAction(UIAlertAction(title: "Cancel", style: .default))

        self.present(alert, animated: true, completion: nil)
    }
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return stocks.count
    }

    /*override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Stocks"
    }*/
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "LabelCell", for: indexPath)

        cell.textLabel?.text = stocks[indexPath.row]
        return cell
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == UITableViewCell.EditingStyle.delete {
            stocks.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: UITableView.RowAnimation.automatic)
        }
    }
}
