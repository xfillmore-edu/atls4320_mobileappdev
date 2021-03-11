//
//  DataHandler.swift
//

import Foundation

class DataHandler {
    var fdata = FactRaw()
    var onDataUpdate: ((_ data: [Fact]) -> Void)?

    func reqJSON(_ val: Int) {
        // API: numbersapi.com
        let hdrs = [
            "Content-Type": "application/json"
        ]
        let ftype = (Int.random(in: 0...2) == 0) ? "math" : "trivia" // 2:1 odds get trivia instead of math
        let path = "http://numbersapi.com/"+String(val)+"/"+ftype

        guard let url_ = URL(string: path)
        else {
            print("Error creating URL from string")
            return
        }

        var request = URLRequest(url: url_, timeoutInterval: 10.0)
        request.httpMethod = "GET"
        request.allHTTPHeaderFields = hdrs

        let session = URLSession.shared.dataTask(with: request, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            print("Returned with status code \(statusCode)")

            guard statusCode == 200
            else {
                print("Bad status code: \(error!)")
                return
            }

            DispatchQueue.main.async {
                self.parseJSON(data!)
                print(data!)
            }

        })
        
        session.resume()
    }
    
    func parseJSON(_ data: Data) {
        
        do {
            let apiData = try JSONDecoder().decode(FactRaw.self, from: data)
            for f in apiData.content {
                fdata.content.append(f)
                print("Created content: \(fdata)")
            }
        }
        catch let err {
            print("JSON parsing error: \(err.localizedDescription)")
            return
        }

        onDataUpdate?(fdata.content)
    }
    
    func fFetch() -> String {
        var fstr : String = ""
        if fdata.content[0].found {
            fstr = fdata.content[0].text
        }
        else {
            fstr = "Could not find a fact for \(fdata.content[0].number)"
        }

        return fstr
    }
}


