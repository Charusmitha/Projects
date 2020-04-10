const validWords = {
    "TEA" : "TEA",
    "EAT" : "EAT",
    "TEE" : "TEE",
    "PEA" : "PEA",
    "PET" : "PET",
    "APE" : "APE",
    "PIE" : "PIE",
    "PEN" : "PEN",
    "TEN" : "TEN",
    "TIN" : "TIN",
    "TIP" : "TIP",
    "ATE" : "ATE",
    "ASH" : "ASH",
    "BAD" : "BAD",
    "BAN" : "BAN",
    "VAN" : "VAN",
    "BAR" : "BAR",
    "BAY" : "BAY",
    "BIB" : "BIB",
    "CUB" : "CUB",
    "GAP" : "GAP",
    "GUT" : "GUT",
    "HOP" : "HOP",
    "JUG" : "JUG",
    "KEG" : "KEG",
    "LIE" : "LIE",
    "LOG" : "LOG",
    "LOW" : "LOW",
    "MAP" : "MAP",
    "PAW" : "PAW",
    "PIN" : "PIN",
    "SHY" : "SHY",
    "TRY" : "TRY",
};

const prevWords = [];

function addPrevWords({ word, matches }) {
  prevWords.push({ word, matches });
}

const words = {
  validWords,
  prevWords,
  addPrevWords,
};

module.exports = words;