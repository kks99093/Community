/**
  게임목록 더 불러오기
 */

let min = 5
let max = 10
function moreGame(sumId){
	moreButton.style.visibility = 'hidden'
	axios.get('/board/moreGame',{
		params: {
			sumId : sumId,
			min: min,
			max: max
		}
	}).then(function(res){
		for(var i = 0; i<res.data.dDTOList.length; i++){
		let game = document.createElement('div')
		game.setAttribute('id','game')
		game.style.backgroundColor =  res.data.dDTOList[i].win == true ? '#a3cfec' : '#e2b6b3'
		game.style.borderColor = res.data.dDTOList[i].win == true ? '#99b9cf' : '#cea7a7'
		
		//game_info
		let game_info = document.createElement('div')
		game_info.setAttribute('class','game_info')

		let que_type = document.createElement('div')
		que_type.innerHTML = res.data.dDTOList[i].que

		let win_color = document.createElement('div')
		win_color.style.color = res.data.dDTOList[i].win == true ? '#1a78ae' : '#c6443e'
		win_color.innerHTML = res.data.dDTOList[i].win == true ? '승리' : '패배'

		let game_time = document.createElement('div')
		game_time.innerHTML = res.data.dDTOList[i].time[0]+ '분 ' + res.data.dDTOList[i].time[1] + '초'
		let timeDate = document.createElement('div')
		timeDate.innerHTML = res.data.dDTOList[i].timeDate
		
		game_info.appendChild(que_type)
		game_info.appendChild(win_color)
		game_info.appendChild(game_time)
		game_info.appendChild(timeDate)
		game.appendChild(game_info)
		
		// sum_info
		let sum_info = document.createElement('div')
 		sum_info.setAttribute('class','sum_info')

 		let sumTB = document.createElement('div')
 		sumTB.setAttribute('class', 'sumTB')
 		sum_info.appendChild(sumTB)

	    let icon_img = document.createElement('img')
	    icon_img.setAttribute('class', 'icon')
	    icon_img.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].champId+'.png')
	    sumTB.appendChild(icon_img)
	
	    let spell = document.createElement('div')
	    spell.setAttribute('class','spell')
	    sumTB.appendChild(spell)
	    let spell_img1 = document.createElement('img')
	    spell_img1.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/spell/'+res.data.dDTOList[i].spell1Id +'.png')
	    let spell_img2 = document.createElement('img')
	    spell_img2.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/spell/'+res.data.dDTOList[i].spell2Id +'.png')
	    spell.appendChild(spell_img1)
	    spell.appendChild(spell_img2)
	    
		let perk = document.createElement('div')
	    perk.setAttribute('class','perk')
	    sumTB.appendChild(perk)
	    let perk_img1 = document.createElement('img')
	    perk_img1.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/img/'+ res.data.dDTOList[i].perk0Icon)
	    let perk_img2 = document.createElement('img')
	    perk_img2.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/img/'+ res.data.dDTOList[i].perkSubStyleIcon)
	    perk.appendChild(perk_img1)
	    perk.appendChild(perk_img2)
	
	    let champ_nm = document.createElement('div')
	    champ_nm.innerHTML = res.data.dDTOList[i].champNm
	    sum_info.appendChild(champ_nm)
	    game.appendChild(sum_info)
	    
	    
		//detail_info
		let detail_info = document.createElement('div')
	    detail_info.setAttribute('class', 'detail_info')
	
	    let kda = document.createElement('div')
	    kda.setAttribute('class','kda')
	    let kda_num = document.createElement('div')
	    kda_num.innerHTML = res.data.dDTOList[i].kills + " / "
	    let kda_span = document.createElement('span')
	    kda_span.setAttribute('id', 'deathSc')
	    kda_span.innerHTML = res.data.dDTOList[i].deaths + " / "
	    kda_num.appendChild(kda_span)
	    kda_num.innerHTML += res.data.dDTOList[i].assists
	
	    let kda_grade = document.createElement('div')
	    kda_grade.innerHTML = ((res.data.dDTOList[i].kills + res.data.dDTOList[i].assists)/res.data.dDTOList[i].deaths).toFixed(2) + " 평점"
	    kda.appendChild(kda_num)
	    kda.appendChild(kda_grade)
	    
	    let sub_info = document.createElement('div')
	    sub_info.setAttribute('class', 'sub_info')
	    let champLv = document.createElement('div')
	    champLv.innerHTML = '레벨' + res.data.dDTOList[i].champLevel
	    sub_info.appendChild(champLv)
	
	    let cs = document.createElement('div')
	    cs.innerHTML = res.data.dDTOList[i].totalMinionsKilled + '(' + (res.data.dDTOList[i].totalMinionsKilled/res.data.dDTOList[i].time[0]).toFixed(1) + ") CS"
	    sub_info.appendChild(cs)
	
	    let killper = document.createElement('div')
	    sub_info.appendChild(killper)
	    let killperSpan = document.createElement('span')
	    killperSpan.setAttribute('id', 'killPer')
	    killperSpan.innerHTML = '킬 관여 ' + Math.round(((res.data.dDTOList[i].kills + res.data.dDTOList[i].assists)/res.data.dDTOList[i].totalKill)*100)+ '%'
	    killper.appendChild(killperSpan)
	    sub_info.appendChild(killper)
	
	    let vision = document.createElement('div')
	    vision.innerHTML = '시야점수'
	    sub_info.appendChild(vision)
	
	    let visionScore = document.createElement('div')
	    visionScore.innerHTML = res.data.dDTOList[i].visionScore
	    sub_info.appendChild(visionScore)
		
	    detail_info.appendChild(kda)
		detail_info.appendChild(sub_info)
	    game.appendChild(detail_info)
	    
		//item_info
		
		let item_info = document.createElement('div')
	    item_info.setAttribute('class','item_info')
	
	    let line_first = document.createElement('div')
	    line_first.setAttribute('class', 'line_first')
	    let item0 = document.createElement('img')
	    item0.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item0.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item0+'.png')
		item0.setAttribute('class','margin_1px')
	    let item1 = document.createElement('img')
	    item1.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item1.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item1+'.png')
		item1.setAttribute('class','margin_1px')
	    let item2 = document.createElement('img')
	    item2.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item2.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item2+'.png')
		item2.setAttribute('class','margin_1px')
	    let item6 = document.createElement('img')
	    item6.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item6.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item6+'.png')
		item6.setAttribute('class','margin_1px')
	    line_first.appendChild(item0)
	    line_first.appendChild(item1)
	    line_first.appendChild(item2)
	    line_first.appendChild(item6)
	    item_info.appendChild(line_first)
	
	    let line_second = document.createElement('div')
	    line_second.setAttribute('class', 'line_second')
	    let item3 = document.createElement('img')
	    item3.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item3.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item3+'.png')
		item3.setAttribute('class','margin_1px')
	    let item4 = document.createElement('img')
	    item4.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item4.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item4+'.png')
		item4.setAttribute('class','margin_1px')
	    let item5 = document.createElement('img')
	    item5.setAttribute('onerror','this.src="/res/img/opacity1.png";' )
	    item5.setAttribute('src', 'http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/'+res.data.dDTOList[i].item5+'.png')
		item5.setAttribute('class','margin_1px')
	    line_second.appendChild(item3)
	    line_second.appendChild(item4)
	    line_second.appendChild(item5)
	    item_info.appendChild(line_second)
	
	    game.appendChild(item_info)
	    
		//team_info
		
		let team_info = document.createElement('div')
	    team_info.setAttribute('class', 'team_info')
	
	    let blueTeam = document.createElement('div')
	    blueTeam.setAttribute('class', 'blueTeam')
	
	    let btImg1 = document.createElement('img')
	    btImg1.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].blueTeam.champList[0]+'.png')
	    blueTeam.appendChild(btImg1)
	    let btImg2 = document.createElement('img')
	    btImg2.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].blueTeam.champList[1]+'.png')
	    blueTeam.appendChild(btImg2)
	    let btImg3 = document.createElement('img')
	    btImg3.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].blueTeam.champList[2]+'.png')
	    blueTeam.appendChild(btImg3)
	    let btImg4 = document.createElement('img')
	    btImg4.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].blueTeam.champList[3]+'.png')
	    blueTeam.appendChild(btImg4)
	    let btImg5 = document.createElement('img')
	    btImg5.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].blueTeam.champList[4]+'.png')
	    blueTeam.appendChild(btImg5)
	
	    let redTeam = document.createElement('div')
	    redTeam.setAttribute('class','redTeam')
	    
	    let rtImg1 = document.createElement('img')
	    rtImg1.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].redTeam.champList[0]+'.png')
	    redTeam.appendChild(rtImg1)
	    let rtImg2 = document.createElement('img')
	    rtImg2.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].redTeam.champList[1]+'.png')
	    redTeam.appendChild(rtImg2)
	    let rtImg3 = document.createElement('img')
	    rtImg3.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].redTeam.champList[2]+'.png')
	    redTeam.appendChild(rtImg3)
	    let rtImg4 = document.createElement('img')
	    rtImg4.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].redTeam.champList[3]+'.png')
	    redTeam.appendChild(rtImg4)
	    let rtImg5 = document.createElement('img')
	    rtImg5.setAttribute('src','http://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/'+res.data.dDTOList[i].redTeam.champList[4]+'.png')
	    redTeam.appendChild(rtImg5)
	
	    team_info.appendChild(blueTeam)
	    team_info.appendChild(redTeam)
	
	    game.appendChild(team_info)
		//
		search_bottom.appendChild(game)
		}
		
		min += 5
		max += 5
		moreButton.style.visibility = 'visible'
	})
}