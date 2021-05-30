$('#submitTotal').on('click', () => {
    const total = $('#totalComponent').val();
    addComponents(total);
    addResultTotal();
    addCalculateEvent();
    addReturnEvent();
});

const addComponents = (totalComponent = 0) => {
    let components = `
        <div id="components">
            <h2>Komponen Penilaian</h2>
    `;

    for (let i = 0; i < totalComponent; i++) {
        components += `
            <div id="component${i}" class="component">
                <input type="number" class="score" min="0" placeholder="0"> x
                <input type="number" class="weight" min="0" placeholder="0">%
            </div>
        `;
    };

    components += `
            <button type="submit" id="calculate" class="button">Calculate</button>
            <button type="submit" id="return" class="button">Return</button>
        </div>
    `;

    $('#main').empty().append(components);
};

const addResultTotal = () => {
    const components = `
        <h2>Data Nilai</h2>
        <h3>Total Bobot: <span id="totalWeight">0</span>%</h3>
        <h3>Total Nilai Berbobot: <span id="totalScore">0</span></h3>
        <h3>Nilai Akhir: <span id="finalScore">...</span></h3>
    `;

    $('#result').empty().append(components);
};

const addCalculateEvent = () => {
    let totalScore = 0;
    let totalWeight = 0;
    $('#calculate').on('click', () => {
        $('.component').each(function() {
            let score = $(this).children().get(0).value;
            let weight = $(this).children().get(1).value;
            score = (score === "") ? 0 : score;
            weight = (weight === "") ? 0 : weight;
            totalScore += score * (weight / 100);
            totalWeight += parseInt(weight);
        });
        $('#totalWeight').text(totalWeight);
        $('#totalScore').text(totalScore);
        showGrade(totalScore);
    });
};

const showGrade = (totalScore) => {
    if (totalScore >= 85) {
        $('#finalScore').text("A");
    } else if (totalScore >= 80) {
        $('#finalScore').text("A-");
    } else if (totalScore >= 75) {
        $('#finalScore').text("B+");
    } else if (totalScore >= 70) {
        $('#finalScore').text("B");
    } else if (totalScore >= 65) {
        $('#finalScore').text("B-");
    } else if (totalScore >= 60) {
        $('#finalScore').text("C+");
    } else if (totalScore >= 55) {
        $('#finalScore').text("C");
    } else if (totalScore >= 40) {
        $('#finalScore').text("D");
    } else {
        $('#finalScore').text("E");
    }
};

const addReturnEvent = () => {
    $('#return').on('click', () => {
        location.reload();
    });
};