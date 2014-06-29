package org.javaz.trampoline;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.javaz.trampoline.Loop.*;

public class Main {

    private List<String> sentences = Arrays.asList(
        "Dammit, I’m mad!",
        "Rise to vote, sir!",
        "Never odd or even",
        "Never odd and even",
        "Was it a car or a cat I saw?",
        "Was it a car or a dog I saw?",
        "Doc, note: I dissent. A fast never prevents a fatness. I diet on cod.",
        VERY_LONG_PALINDROME
    );


    public static void main(String[] args) {
        new Main().testPalindromes();
    }

    private void testPalindromes() {
        sentences.stream()
                .filter(new PalindromePredicate())
                .forEach(System.out::println);
    }

    private static void loop() {
        // System.out.println( trampolineSum(10000) );
        trampolineLoop(10000, System.out::println);
    }

    private static final String VERY_LONG_PALINDROME =
            "Star? Not I! Movie – it too has a star in or a cameo who wore mask – cast are livewires. " +
            "Soda-pop straws are sold, as part-encased a hot tin, I saw it in mad dog I met. Is dog rosy? Tie-dye booths in rocks. " +
            "All ewes lessen ill. I see sheep in Syria? He, not I, deep in Syria, has done. No one radio drew old one. " +
            "Many moths – I fondle his; no lemons are sold. Loot delis, yob, moths in a deli bundle his tin. Pins to net a ball I won – pins burst input. I loot to get a looter a spot paler. Arm a damsel – doom a dam. Not a base camera was in a frost, first on knees on top spot. Now a camera was a widened dam. " +
            "Ask: Cold, do we dye? No, hot – push tap, set on to hosepipe. Nuts in a pod liven. " +
            "A chasm regrets a motto of a fine veto of wars. Too bad – I all won. A sadist sent cadets – a war reign a hero derides. A bad loser, a seer, tossed a cradle – he begat to cosset – a minaret for Carole, Beryl, Nora. We’re not as poor to self. " +
            "I risk cold as main is tidal. As not one to delay burden, I don’t set it on “hot”. A foot made free pie race losses runnier. As draw won pull, eye won nose. Vile hero saw order it was in – even a moron saw it – no, witnessed it: Llama drops – ark riots. Evil P.M. in a sorer opus enacts all laws but worst arose. Grab a nosey llama – nil lesser good, same nicer omen. " +
            "In pins? No, it is open. If a top spins, dip in soot. " +
            "Madam, as I desire, dictates: Pull aside, damsels, I set a rag not for a state bastion. A test I won e.g. a contest I won. " +
            "Kidnap, in part, an idle hero. Megastars, red, rosy, tied no tie. Blast! A hero! We do risk a yeti’s opposition! " +
            "He too has a wee bagel still up to here held. " +
            "Demigods pack no mask, cap nor a bonnet, for at last a case is open – I left a tip – it wets. A dog wets too. Radios to help pay my tip, pull a tip. " +
            "Ale, zoo beer, frets yon animal. Can it? New sex arose but, we sots, not to panic – it’s ale – did I barrel? Did I lose diadem, rare carrot in a jar of mine? Droop as tops sag – unseen knots. " +
            "A cat ate straw as buck risk cud; evil foe, nil a red nag ate? Bah! Plan it – silage. Model foot in arboreta. " +
            "I, dark Satanist, set fire – voodoo – to slat. I design a metal as parrot, I deem it now. One vast sum is no ten in set – amen! Indeed, nine drag a yam, nine drag a tie. Dame nabs flower; can we help man? Woman is worse nob. " +
            "Mud level rose, so refill a rut. A nag of iron I made to trot I defied – I risk leg and its ulnae. Can a pen I felt to bid dollar or recite open a crate, open a cradle, his garret? " +
            "Sample hot Edam in a pan. I’m a rotten digger – often garden I plan, I agreed; All agreed? Aye, bore ensign; I’d a veto – I did lose us site. Wool to hem us? No, cotton. Site pen in acacias or petals a last angel bee frets in. " +
            "I met a gorilla (simian); a mate got top snug Noel fire-lit role. Manet, Pagnol, both girdle his reed bogs. " +
            "Flan I reviled, a vet nods to order it, Bob, and assign it. Totem users go help mates pull as eye meets eye. Son – mine – pots a free pie, yes? No. Left a tip? Order a dish to get. A ring is worn – it is gold. Log no Latin in a monsignor, wet or wise. Many a menu to note carrot. " +
            "Cat in a boot loots; As I live, do not tell! A bare pussy, as flat on fire, I know loots guns, fires a baton, nets a hero my ale drop made too lax. " +
            "If it is to rain, a man is a sign; I wore macs, no melons rot. I use moths if rats relive, sir, or retire. " +
            "Vendor pays: I admire vendee, his pots net roe. Nine dames order an opal fan; I’ll ask cold log fire vendor to log igloo frost. Under Flat Six exist no devils. " +
            "Marxist nods to Lenin. To Lenin I say: “Mama is a deb, besides a bad dosser.” " +
            "Gen it up to get “ova” for “egg”. I recall a tarot code: yell at a dessert side-dish sale. Yes/nos a task cartel put correlate: E.S.P. rocks a man. I am a man, am no cad, I’m aware where it’s at! " +
            "Fire! Its an ogre-god to help, man, as I go. Do not swap; draw, pull a troll! " +
            "It’s not a cat I milk – calf, for a fee, sews a button – knit or tie damsel over us. Mined gold lode I fill until red nudes I met in a moor-top bar can. I sit, I fill a diary – trap nine men in ten-part net – oh, sir, I ask, cod nose? No, damp eel. " +
            "So, to get a name! I say, Al! I am Al! Last, I felt, to breed, deer begat. " +
            "To can I tie tissue – damp – or deliver Omani artist – a man of Islam. " +
            "In a den mad dogs lived on minis a signor who lived afore targets in at. As eremites pull, I, we, surf, fantasise, mend a bad eye. No hero met satyr; Tony, as I stressed, won’t, so cosset satyr. " +
            "A vet on isles made us sign it, a name. Foe man one sub. " +
            "Aside no dell I fret a wallaby; metal ferrets yodel, like so. On a wall I ate rye. Bored? No, was I rapt! One more calf? O.K., calf, one more, bossy! No! Lock cabin, rob yam, sip martini. Megastar was in a risk. " +
            "Cat? No, I’m a dog; I’m a sad loyal pet. A design I wore – kilts (a clan); if net drawn, I put it up. Royal spots snag – royal prevents rift. " +
            "Composer, good diet, are both super, God – label it a love of art, lustre. Video bored, no wise tale e.g. a mini tale – no sagas seen. Knack: cede no foes a canal. " +
            "Pay – as I sign I lie; clear sin it is; e.g. “Amadeus” sign I – lira for ecu, decimal – sin as liar. " +
            "Trad artistes pull a doom, a drawer won’t. " +
            "Is it sold loot? No, I suffered loss. A man is god; Amen! I came nice Tahiti (sic). " +
            "It’s ale for a ban if for a fast – is role to help mash turnip? Use zoo? No – grasp order – use no zoos. Warts on time did sag. " +
            "No grade “X” “A” Level? Oh, “A”! I’d a “B” or a “C”. So – pot? No, we lop. Date? Take no date! Bah! Play L.P. " +
            "Miss (a lass, all right?) flew to space in NASA era. Rose no (zero) cadets ate raw. As a wise tart I fined rags red Lenin, we help pay bet – a risk – cash to Brian. I put a clam in a pool – a pool wets. " +
            "Mahdi puts a stop to harem – miss it in one vote, lost in one, veto of none. Post-op, no tonsil; I ate; no tastier, eh? We sleep at noon time so I dare not at one; no time stops as I time tides. A bed: under it, roll; in a mania, panic! " +
            "In a pond I did as Eros as Lee felt tenrec. “Ink” – list it under “I”. Termites put pen in a way. Democrats wonder, I too. To slay moths a dog did. " +
            "I saw elf; elf, far now, is a devilish taboo, rag-naked. I hid a bootleg disc. I, saboteur, toss it in. Oops! No legs! Laminated, a cask, conker in it, negates all if it is simple. " +
            "Hot pages are in a mag, nor will I peer, familiar tat, so lewd, native rot. Toner, ewe wore no trace; vagabond ewes do. Oh, Ada! Have pity! A pitiable eel – “Oh wet am I!” – to save, note: bite gill as I do. " +
            "Call a matador minor, eh? As I live, don’t! Is torero no rigid animal debaser if tipsy? Ale drew esteem in a matador. A bolero, monks I rate play or go dig rocks; a can I step on. " +
            "Go! Gas – it evades a bedsit – set a roost on fire. Boss sent a faded eclair to green imp or dog, I’d don a belt to boot it; if Ada hid a boot, panic. " +
            "I mock comic in a mask, comedian is a wit if for eventide. Vole no emu loved is not a ferret, so pet or witness a weasel if not. I hired less, am not so bossy, as yet amateur. " +
            "To stir evil, Edna can impugn a hotel: bad loos, hot on Elba: I may melt. Tart solicits it rawer, gets it rare. Push crate open; I ram buses, use no trams. " +
            "Did I say, not to idiot nor a bare ferret, to trap rat, strap loops rat? Stewpot was on. Hot? I was red! Lessen it! Fine man on pot? No, pen inside by a bad law. So I made rips – nine delays. " +
            "Some Roman items in a.m. ordered “Is room for a ban?” “It is,” I voted: I sat pews in aisle. Beryl, no tiro to my burden, made off for a contest, I won kiss. I may raid fine dales. I raid lochs if I to help am. " +
            "Forecast for Clare v. Essex: If no rain, a man is ref. Fusspots net foxes. " +
            "Senor is a gnome, latinos’ bad eyesore. Help misses run to border, Casanova, now, or drab hotel. " +
            "Ma has a heron; I sleep, pet’s on nose, sir! Rev. I rag loved art live – fine poser. Ultra-plan: I feign, I lie: cedar to disperse – last one? No, last six. Enamel bonnet for a dark car to toss a snail at. In it all, Eve lost; Seth’s a hero slain on a trap – Rise, Sir Ogre Tamer. " +
            "Upon Siamese box I draw design. I, knight able to help, missed an alp seen in Tangier of fine metal pots. Tin I mined rages – order nine, melt ten. Tone radios; tones are not to concur. Ten-tone radar I bomb – best fire-lit so hostel side meets eerie mini red domicile. A gulf to get is not a rare tale; no time to nod. " +
            "Row on, evil yobs, tug, pull. If dogs drowse, fill a rut. An era’s drawers draw. Put in mid-field in a band I dig a tub deep. Staff on a remit did refill a minaret. " +
            "Sam’s a name held in a flat, or, sir, bedsit. I wonder, is it illicit ore? No ties? A bit under? Retarded? Is ‘owt amiss? I’m on pot; not so Cecil, a posh guy a hero met. A red date was not to last so Cecil sat. " +
            "Tip? An iota to pay, a dot; sad, I drop item. I’d ask, call, Odin, a Norseman’s god: “Pay payee we owe radio dosh o.n.o.” I to me? No, I to media. " +
            "Peril in golf – is ball a “fore”? K.O.! " +
            "Vexed I am re my raw desires. Alto has eye on nose but tone-muser pianist is level-eyed. I lost a tie. Blast! In uni no grades are musts. Avast! Never port! Sea may be rut. " +
            "Part on rose? – It’s a petal. Define metal: " +
            "Tin is . (I gulp!) can! " +
            "I am a fine posse man, I pull a ton. Ron, a man I put on, I made suffer of evil emu’s sadism. Leo’s never a baron – a bad loss but evil – topple him, Leo’s lad. Assign a pen, can I? A pal is note decoding. " +
            "Is damp mule tail-less? No, ill; I breed for its tone. Radio speed, to grower, grew. Open a lot? No, stamp it; if for a free peso – not ecu -deign it. Times ago stone rates, e.g. at Scilly, display a wont. " +
            "No wish to get a design I, Sir Des, I’ve let? No bus sees Xmas fir. O.K. – cab – tart it up; tie lots – diamond, log or tinsel; first end errata edit. So “le vin (A.C.)”, Martini, Pils lager, one tonic. " +
            "I pegged a ball up to here when I got a top star role, Beryl. Gun is too big – won’t I menace? Yes? No? " +
            "Ill? A cold? Abet icecap’s nip. U.S.A. meets E.E.C. inside tacit sale – see! Beg a cotton tie, ma! No trial, so dodo traps exist. Arabs under-admire card label good hood stole. " +
            "In rage erupted Etna. Will a rotunda, bare villa, to tyro. Lack car? Non-U! Get a mini! My, my, Ella, more drums per gong; get a frog – nil less. Rod, never ever sneer. Got to? " +
            "I disperse last pair of devils (ah!) here today or else order cash to breed emus. Said I: “Are both superlative?” C.I.D. assign it lemon peel still. I wore halo of one bottle from a ref (football) – a tip; so hit last ego slap a mate got. " +
            "Late p.m. I saw gnu here (non-a.m.) or an idea got a dog to nod – I made felt to boot. " +
            "Fill in a lad? Nay, not all, Edna – lash to buoy. Did you biff one Venus? Not I! “Broth, girl!” ladies ordered – “No, with gin!” – a fine plate, maybe suet; no carton I made rots in it. " +
            "Med: a hill, Etna, clears in it. Ali, Emir, to slap in/slam in. All in all I made bad losers sign it – alibi. Set a lap for a level bat. " +
            "A bed, sir, eh? To put cat now? Drat! Such an idyll of a dog’s lair! That`s it, open it – a cage! Big nit sent rat! Some day (A.D.) send ewe. No, draw a pot now, do! Of wary rat in a six ton tub. " +
            "Edna, ask satyr: “Tel. a.m.?” No, tel. p.m.; Israeli tuner is damp. Use item: “Anna Regina”. No! Dye main room (“salle”) red! " +
            "Nice caps for a sea cadet in U.S.A. – Now I, space cadet, am it, sea vessel rep. Pin it on Maria, help Maria fondle her fine hotpot. No! Meet; set up to net, avoid a lesion. Set acid arena: Bruno one, Reg nil. Like it to sign in? Even I am nine-toed! I vote votes. " +
            "Oh, can a nose-rut annoy? No, best is Dorset. I know, as liar, to snoop, malign. “I’ll order it to get a bedroom door,” began a miser I fed. " +
            "Am I to peer, fan? Is a door by metal? Ere sun-up, drowse, nod, lose magnet. Food? Buns? I’ll ask. Corn? I’ll ask. Corn – I snack. Cats snack (cold rat). Sum for a bag: nil. First, is remit “traps in net”? Yes, on a par. Coots yell over a dam I made. Bared nudist went a foot, I made roots. I tip a canon: “Row, sir, at same tide; man one: row tug.” " +
            "Sewer of denim axes a wide tail – a terror recipe to hero made manic. I, to resign? I ? Never! " +
            "“OFT I FELT ITS SENSUOUSNESS” – title fit for evening is erotic; I named a more hot epic – error retaliated – I was examined for ewe’s gut, wore no named item. " +
            "A star is worn on a cap, it is too red. Am I too fat? Newts I’d under a bed. Am I mad? Are volleys too crap? A nosey tennis part-timer sits rifling a bar of mustard. " +
            "Lock cans, stack cans in rocks, all in rocks, all I snub. Do often games, old ones, word-pun use; relate, my brood, as in a free pot I made fires, I manage brood. Moor debate got tired rolling, I lampoon, so trail saw on kites. " +
            "Rod sits, ebony on nature, so Nana chose to veto video. Ten in main evening is O.T.T. i.e. killing; Ere noon, urban eradicates noise, lad, I ovate not. Put esteem on top (to hen, if reheld). " +
            "No fair ample hair – am not I nipper-less? Eva estimated ace caps I won as united. A Caesar of space, Cinderella’s moor, Niamey Don (a Niger-an name), ties up mad sire, nut! I, Lear, simpleton male, try tasks “A” and “E” " +
            "but not “XI”. Sanitary raw food won top award one Wednesday – a demo. " +
            "Start nesting, I beg a cat. I? Nepotist? Ah, trials, God! A folly, Dinah, custard won’t act up; other is debatable. Velar: of palate; sibilating is “s”. " +
            "Resold: a bed, a mill, an ill animal – snip, also trim. Eilat in Israel can tell I had ‘em. Tin I stored (am I not raconteuse?) by a metal pen. If a night, I wondered, rose, I’d all right orbit on sun, even off. " +
            "I buoy, did you? Both Sal and Ella, Tony and Alan (“Ill if too bottle-fed, am I?”) do not. God! A toga! Ed in a Roman one, rehung! Was I, M.P. et al., to get a map? Also get salt? I, hospital lab to offer, am, or felt to be, no fool – a hero. " +
            "Will it sleep? No, melting is sad ice. Vital re-push to be raid, I assume. Deer, both sacred roes, Leroy (a doter, eh?) has lived for. I, apt sales rep’s idiot to greens, revere vendors selling or fat egg-nog reps. " +
            "Murder O’Malley, my mini mate – gun on rack. Calory total: liver, a bad nut or all I wanted (“et puree garnie”): lots. “Do, oh do, ogle bald racer,” I’m dared – N.U.S. bar at six. " +
            "Esparto, dodo’s lair to name it, not to cage bees, elasticated, is nice. Esteem, as up in space, cite bad local lions, eye can emit now. G.I. boots in ugly rebel or rat’s potato gin (eh?) were hot. Pull a bad egg – epic, I note, no regal slip in it. Ram can . (I’ve lost idea!) " +
            "Tarred nets, rifles, nitro, gold – no maid stole it. Put it, rat, back or if Sam (“X”) sees sub on televised rising, I sedate Goths. I won’t – no way. " +
            "Alps, idyllic stage set, are not so gas-emitting, I educe. To nose, peer, far off, I tip mats onto lane. Power grew or got deep so I dare not stir. Of deer, billions sell. I ate lump – mad sign, I do cede – tonsil a pain, acne pang is sad also. Elm I help pot, live – tub’s sold; a ban or a bar, even so, elms, I’d assume, live for. Effused am I not, up in a manor, not all up in a mess. " +
            "Open if a main A.C. plug is in it. " +
            "Late men I fed late – pasties or not. “Rapture” by a maestro prevents a vast sum erased. " +
            "Argon in units, albeit at solid eye level, sits in a . (I presume not) . tube, son. No eyes: a hot laser – is Ed wary? " +
            "Mermaid, ex- evoker of all A.B.s, I flog. Nil I repaid. Emotion! Emotion, oh so do I dare, woe! " +
            "Wee yap-yap dog’s name’s Ron. An idol lacks a dime tip, or did, as today a potato in a pitta slice costs a lot – tons. A wet adder ate more hay. Ugh! So, pal, ice cost on top? No, miss, I’m a two-sided rat, erred nut, I base it on erotic ill; It is I, red now; it is debris, rot. " +
            "Alf, an idle he-man as “master animal lifer” did time, ran off at speed, but a G.I. did nab an idle if dim nit. Upwards rewards are natural life’s words, God. Fill up guts, boy, live now or do not emit one later. A rat on site got flu. " +
            "Gaelic, I’m odd Erin, I’m Eire, esteemed islet. So hostile rifts ebb. Mob, I.R.A., dare not net R.U.C. – no cotton. Erase not, so I dare not nettle men in red rose garden – I’m in it. " +
            "Stop late men if foreign at nine. Esplanades, simple hotel, bath, gin – king is Edward IX; obese; Ma is no pure mater. Go! Rise, sir; part anon. " +
            "I also rehash tests – ‘O’ Level Latin, Italian. S.A.S., so, to track radar. Often nobleman exists alone – not sales reps – I do. Trade ceiling, i.e. final part, lures open if evil trade. " +
            "Volga River rises on no steppe. Elsinore has a hamlet – Oh, Bard, row on Avon! " +
            "A sacred robot nurses simple hero’s eye; dabs on it a lemon. Gas, iron, Essex often stops, suffers in a mania. Ron fixes several crofts, acer of maple. Hot, I fish; cold, I arise laden; if diary amiss, I know it set no car off. Foe-damned ruby motor, it only rebels. " +
            "Ian I swept aside to visit, in a bar of moorside red, Romanis met in a more mossy ale den. Inspired am I, Oswald. A bay bed is nine p on top. No name, niftiness- elder saw it. Oh no! Saw top wet star’s pool – part star, part otter. Refer a baron to idiot, Tony, as I did. " +
            "Smart ones use submarine. " +
            "Poet, arch-super-artiste, grew artistic. I lost rattle; my amiable, not oh so old, able to hang up, mina, can deliver it, so true. “Ta, matey!” – says so Boston (Mass.) elder I hit. " +
            "On file S.A.E. was sent – I wrote poster re fat on side, volume one – loved it, never off it, I was in. Aide mocks a manic; I mock comic, I nap: too bad I had a fit, I too. Bottle ban odd, I go drop mine, ergo trial ceded a fatness, sober if not so, or a test is debased. " +
            "A vet is agog – no pet’s in a cask – corgi dog, royal pet, a risk no more. " +
            "Lob a rod at a man I meet. Sewer delays pit fires – a bedlam in a dig – iron ore rots it. No devil is a hero – Nimrod. " +
            "At a mall a cod is all I get. I bet on Eva, so Tim ate whole eel bait, I pay tip, Eva had a hood sewed. No B.A. gave car to Nero, we were not to rev it and we lost a trail; I’m a free pill, I wrong a man. I erase gap; to help miss it, I fill a set. A gent in ire knocks a cadet. " +
            "Animals’ gel on spoon – it is so true to basics – I’d gel; too bad I hide kangaroo baths – I lived as I won raffle, flew as I did go, dash, to my, also too tired now, star comedy: A wan, inept, upset I’m retired, nut; its ilk, nicer. Nettle feels a sore; sad, I did no panic in a pain, am an ill or tired, nude, based item; it is a spot. " +
            "Semitone, not a tone, radios emit; no, on tape; elsewhere it’s a tone. " +
            "Tail is not on; pots open on foot, even on it, so let oven (on, it is) simmer – a hotpot’s a stupid ham stew. " +
            "Loop a loop, animal – cat up in air. " +
            "Both sacks I rate by apple hewn in elder’s garden if it rates, I was aware – tasted a core. " +
            "Zones or areas, Annie, cap, so twelfth girl, lass, alas, simply (alpha beta) done, Kate. Tadpole won top Oscar, Obadiah, “O” Level axed. " +
            "Argon gas did emit no straw, so ozone sure drops argon, oozes up in Ruth’s ample hotel or sits afar off in a bar – of elastic, is it? " +
            "I hate cinema; cinema dogs in a mass. Older effusion to old – lost, is it now? Reward: a mood. " +
            "All upsets it. " +
            "Radar trails an Islamic educer of a riling issue, damages it in Israel. Ceiling is, I say, a plan, a case of one deck. Can knees sag as one Latin image elates, I wonder? " +
            "Oboe diverts ultra foe, volatile bald ogre – push to berate; I’d do, ogre. So, p.m., Oct. first, never play organ’s stops – lay or put it up in ward ten. " +
            "Final cast like rowing – I sedate play, old as am I, God! Am I! On tacks I ran; I saw rats. A Gemini tramp is May born. " +
            "I back colony’s sober omen of lack of lace. Rome, not Paris, a wonder. " +
            "Obey retail law – a noose killed oyster. Reflate my ball, a water-filled one. Disabuse no name of emanating issue. " +
            "Damsels, I note, vary tastes so cost now desserts. I say no! Try taste more honeyed. A bad nemesis at naff ruse will upset. I, mere Satanist, e.g. rater of a devil – (Oh wrong is a sin!) – I’m no devil’s god, damned. " +
            "Animals, if on a mat, sit. Rain, a more vile drop, made us site it in a cottage. Breed deer – bottle fits a llama. " +
            "I lay, as I emanate, go to sleep, mad ones on docks – air is hot. Entrap, net, nine men in party raid – all if it is in a crab-pot room, an itemised, under-lit, nullified old log den – I’m sure voles made it rot in knot. " +
            "Tubas we see far off lack limit. A cat on still or tall upward paws to no dog is an ample hot-dog, ergo nastier if tastier, eh? We, raw amid a conman, a mama in a mask, corpse et al., err. " +
            "Octuple tracks at a son’s eyelash side distressed a tall eye doctor, a tall ace, rigger of a vote: got put in egress; odd, abased, is ebbed, as I am, Amy, asinine lot! Nine lots! Don’t six rams live? Don’t six exist? " +
            "Alfred, nuts or fool gigolo, trod never if gold locks all in a flap on a red rose; made nine or ten stops. " +
            "I heed never, I’m Daisy, a prod never, I terrorise viler starfish. To me suitors, no lemons, came rowing. Is a sin a mania? Rot! " +
            "Sit! I fix a looted amp or delay more, hasten not. A baser if snug stool, wonkier, if not – Alf says – super, a ballet to no devil, is a stool too. Ban it, actor, race to no tune. " +
            "May names I wrote wrong (Is no man in it, a long old log?) sit in row, sign irate Goths; I dare drop it. At felon’s eye I peer, fast open – I’m nosey, esteem eyes. All upset, ample hogs resume totting. Is sad nabob tired? Roots don’t evade liver in Alf’s gob. " +
            "Deers I held right; oblong, apt enamel or tile rifle on gun spot to get a man – aim is all. I rogate, minister. Feeble gnats, alas late, prosaic, a canine pet is not to consume hot. " +
            "Loo, wet, issues old idiot; evading, I sneer, obey a deer, gall a deer, gain alpine dragnet for egg I’d net to ram in a pan I made to help master. Rags I held, arcane poet, arcane poetic error, all odd; I bottle fine panacean lust. I’d nag elks I ride if editor toted a minor. I fog a natural life. " +
            "Roses, or level dumb ones – rows in a mown, ample, hewn acre. Wolfsbane made it a garden in May, a garden indeed. " +
            "Nine mates, nine tons I must save now on time – editor raps a late man. G.I.s edit also, too. Do over if tests in a task radiate. Rob ran; I, too, fled. " +
            "“Omega” – list in alphabet. " +
            "A gander, a line of live ducks, irk cubs. A wart, set at a cast on knee, snug as spots. " +
            "A poor denim for a janitor, racer, armed aide, solid idler – rabid; I’d elastic in a pot, tons to sew. " +
            "Tubes or axes went in a clam, in an oyster. Free booze – lap it all up. Pity, my apple hot, so I’d a root stew. God, a stew! Tip it at feline! Posies, a cat’s altar often, no baron packs. A monk caps dog – I meddle here – hot? Pull its leg! A bee was a hoot, eh? " +
            "No, it is opposite. Yaks I rode wore hats, albeit on deity’s orders. Rats age more held in a trap, nip and I know it – set no cage now. " +
            "It’s eta; no, it’s a beta – Tsar of Tonga rates isles. Mad Ed is all upset at cider, is Ed? Is a madam too? Snip? I’d snip, spot a fine position, snip nine more cinemas. " +
            "Do ogres sell in a mall? Yes, on a barge so rats row tubs. " +
            "Wall last canes up or Eros, an imp, lives to irk, rasp or dam all tides sent. I won’t – I was no Roman – even I saw tired row – a sore. He lives on. “No!” we yell. " +
            "Up, now! Wards are in nurses’ sole care. I, peer, fed, am too fat? Oh, not I, test no dined ruby ale; dote not on salad it’s in – I am sad. " +
            "Locks I rifle so troops atone re war. Only rebel or a crofter animates so cottage beheld arcades, so trees are sold, abased. I redo, rehang, I err – a wasted act; nests I’d – as an owl – laid. A boot’s raw foot, even if a foot to master, germs (ah!) can evil do. " +
            "Pan is tune-pipe – so hot notes, paths up to honeydew. " +
            "Odd locks, a maddened (I was aware) macaw on top, spot no seen knots, rifts or fan, I saw. Are maces a baton, madam? Oodles, madam? Rare laptops are too late – got too lit up. " +
            "Nits rub – snip now, I’ll abate, not snip, nits I held. " +
            "Nubile Danish tomboys I led to old loser as no melons I held; no fish to my name. Nod lower, do I dare? No, one nods a hairy snipe. (Edit: one hairy snipe, eh?) See silliness, else we’ll ask cornish to obey deity’s or god’s item. I, God, damn it! I was in it! To Hades, acne trap, sad loser! As warts pop, a dosser I – we – vile rat, sack! Same row, oh woe! Macaroni, rats, as a hoot, tie. I vomit on rats.";
}
