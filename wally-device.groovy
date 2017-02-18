/**
 *  SmartSense Temp/Humidity Sensor
 *
 *  Copyright 2014 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "WallyHome Sensor", namespace: "jarrodmoss", author: "Jarrod Moss") {
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
    capability "Polling"
    capability "Refresh"
	attribute "lastUpdate", "number"
	}

  simulator { }

	tiles {
		valueTile("temperature", "device.temperature", inactiveLabel: false, width: 2, height: 2) {
			state "temperature", label:'${currentValue}°',
				backgroundColors:[
					[value: 31, color: "#153591"],
					[value: 44, color: "#1e9cbb"],
					[value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 95, color: "#d04e00"],
					[value: 96, color: "#bc2323"]
				]
		}

		valueTile("humidity", "device.humidity", inactiveLabel: false) {
			state "humidity", label:'${currentValue}% humidity', unit:""
		}

		valueTile("lastUpdate", "device.lastUpdate", inactiveLabel: false) {
			state "last update", label:'${currentValue} min ago', unit:""
		}

    standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat") {
			state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
		}

		main "temperature", "humidity"
		details(["temperature","humidity","refresh", "lastUpdate"])
	}
}

def poll() {
  log.debug "polling"
  parent.pollChildren()
  //parseEventData(results)
}

def refresh() {
  log.debug "refresh"
  poll()
}

//def parseEventData(Map results){
//  results.each { name, value ->
//    sendEvent(name: name, value: value)
//  }
//}
