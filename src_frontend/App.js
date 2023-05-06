import React, { useEffect, useState } from 'react';
import './App.css';


function App() {
  const [userId, setUserId] = useState('');
  const [description, setDescription] = useState('');
  const [duration, setDuration] = useState('');
  const [stress, setStress] = useState('');
  const [energyLevel, setEnergy] = useState('');
  const [tag, setTag] = useState('');
  const [week, setWeek] = useState('');
  const [month, setMonth] = useState('');
  const [time, setTime] = useState('');
  const [dreams,setDreams]=useState([]);
  
  const [imageSrc, setImageSrc] = useState('');
  const handleAddDream = async () => {
    const response = await fetch('http://localhost:8080/api/v1/login/'+userId, {
      method: 'POST',
      headers:{"Content-Type":"application/json"},
      body: JSON.stringify({tag: tag, week: week, month: month, time: time})
    });

    const blob = await response.blob();
    const url = URL.createObjectURL(blob);
    setImageSrc(url);
  };


  /*const handleAddDream = (e) => {
   
    e.preventDefault()
    fetch("http://localhost:8080/api/v1/login/" + userId, {
    method: "GET"
    }).then(res => res.json())
    .then((result) => {
    setDreams(result);
    
    let value = '';
     
         if (time === 'weekly') {
          value = week;
           
         } else if (time === 'monthly') {
          value = month;
            
         }
    
    }
    )
    }*/

  const handleSubmit = (e) => {
    e.preventDefault()
    const dream={userId,description,duration,stress,energyLevel,tag,week,month}
    console.log(dream)
    fetch("http://localhost:8080/api/v1/login/"+userId,{
      method:"PUT",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(dream)

  }).then(()=>{
    console.log("New dream added")
    
  })
  };


  

  return (
    <div>
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="userId">User ID:</label>
                <input type="text" id="userId" value={userId} onChange={(e) => setUserId(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="description">Description:</label>
                <textarea id="description" value={description} onChange={(e) => setDescription(e.target.value)}
                          required/>
            </div>
            <div>
                <label htmlFor="duration">Duration:</label>
                <input type="text" id="duration" value={duration} onChange={(e) => setDuration(e.target.value)}
                       required/>
            </div>
            <div>
                <label htmlFor="stress">Stress Level:</label>
                <input type="text" id="stress" value={stress} onChange={(e) => setStress(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="energyLevel">Energy Level:</label>
                <input type="text" id="energyLevel" value={energyLevel} onChange={(e) => setEnergy(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="tag" >Tag:</label>
                <select id="tag" value={tag} onChange={(e) => setTag(e.target.value)} required>
                    <option value="">-- Please choose a tag --</option>
                    <option value="Nightmare">Nightmare</option>
                    <option value="Happy">Happy</option>
                    <option value="Weird">Weird</option>
                </select>
            </div>
            <div>
                <label htmlFor="week" >Week:</label>
                <select id="week" value={week} onChange={(e) => setWeek(e.target.value)} required>
                    <option value="">-- Please choose a week --</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </div>
            <div>
                <label htmlFor="month">Month:</label>
                <input type="text" id="month" value={month} onChange={(e) => setMonth(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="time">Generate the graph based on:</label>
                <select id="time" value={time} onChange={(e) => setTime(e.target.value)} required>
                    <option value="">-- Week or month --</option>
                    <option value="weekly">weekly</option>
                    <option value="monthly">monthly</option>
                </select>
            </div>
            <button type="submit">Add Dream</button>
            <button type="button" onClick={(e) => handleAddDream(e)}>Generate</button>
        </form>
        {imageSrc && <img src={imageSrc} alt="Chart" />}
   
    </div>

);
}

export default App;